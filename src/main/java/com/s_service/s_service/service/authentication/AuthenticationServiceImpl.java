package com.s_service.s_service.service.authentication;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Override
    public boolean introspect(String token) {
        return true;
    }
}
