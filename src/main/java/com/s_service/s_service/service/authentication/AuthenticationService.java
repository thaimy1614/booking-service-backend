package com.s_service.s_service.service.authentication;

public interface AuthenticationService {
    boolean introspect(String token);

    void logout(String token);
}
