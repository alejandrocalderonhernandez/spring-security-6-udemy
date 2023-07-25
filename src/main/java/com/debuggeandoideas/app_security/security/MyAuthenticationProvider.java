package com.debuggeandoideas.app_security.security;

import com.debuggeandoideas.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MyAuthenticationProvider implements AuthenticationProvider {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var username = authentication.getName();
        final var pwd = authentication.getCredentials().toString();

        final var customerFromDb = this.customerRepository.findByEmail(username);
        final var customer = customerFromDb.orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        final var customerPwd = customer.getPassword();

        if (passwordEncoder.matches(pwd, customerPwd)) {

            final var authorities = Collections.singletonList(new SimpleGrantedAuthority(customer.getRole()));
            return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
