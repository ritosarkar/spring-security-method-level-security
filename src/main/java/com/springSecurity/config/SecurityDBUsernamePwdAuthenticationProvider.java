package com.springSecurity.config;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@Profile("!production")
@RequiredArgsConstructor
public class SecurityDBUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    public @Nullable Authentication authenticate(@Nullable Authentication authentication) throws AuthenticationException {
        assert authentication != null;
        String username=authentication.getName();
        String password= Objects.requireNonNull(authentication.getCredentials()).toString();
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());

    }

    public boolean supports(@Nullable Class<?> authentication) {
        assert authentication != null;
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
