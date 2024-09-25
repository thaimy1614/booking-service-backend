package com.s_service.s_service.service.authentication;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.s_service.s_service.dto.request.LoginRequest;
import com.s_service.s_service.dto.response.LoginResponse;
import com.s_service.s_service.exception.AppException;
import com.s_service.s_service.exception.ErrorCode;
import com.s_service.s_service.model.Account;
import com.s_service.s_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RedisTemplate<String, String> redisTemplate;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.signer-key}")
    private String KEY;
    @Value("${jwt.expiration-duration}")
    private long EXPIRATION_DURATION;
    @Value("${jwt.refreshable-duration}")
    private String REFRESHABLE_DURATION;

    public LoginResponse authenticate(LoginRequest request) throws JOSEException {
        String email = request.getEmail();
        String username = request.getUsername();
        String password = request.getPassword();
        log.info("{} - {} - {}", request.getEmail(), request.getUsername(), request.getPassword());
        username = (email == null) ? username : email;
        Account authUser = accountRepository.findByEmail(username).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean check = passwordEncoder.matches(password, authUser.getPassword());
        if (!check) {
            return null;
        }
        var token = generateToken(authUser);
        return LoginResponse.builder()
                .token(token)
                .userRole(authUser.getRoles().getRole())
                .build();
    }

    private String generateToken(Account account) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet
                .Builder()
                .subject(account.getId())
                .issuer("Thaidq")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(EXPIRATION_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .claim("scope", account.getRoles().getRole().name())
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(KEY));
        return jwsObject.serialize();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws Exception {
        JWSVerifier verifier = new MACVerifier(KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = (isRefresh) ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(Long.parseLong(REFRESHABLE_DURATION), ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if (!(verified && expiryTime.after(new Date()))) {
            throw new Exception("UNAUTHENTICATED");
        }

        if (isTokenInBlacklist(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new Exception("UNAUTHENTICATED");
        }
        return signedJWT;
    }

    public boolean introspect(String token) {
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isTokenInBlacklist(String jwtId) {
        return redisTemplate.opsForValue().get("bl_" + jwtId) != null;
    }

    private void addTokenToBlacklist(String jwtId, Date expiryTime) {
        redisTemplate.opsForValue().set("bl_" + jwtId, jwtId);
        redisTemplate.expireAt("bl_" + jwtId, expiryTime);
    }

    public void logout(String token) throws Exception {
        var signedJWT = verifyToken(token, true);
        String jId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        addTokenToBlacklist(jId, expiryTime);
    }
}
