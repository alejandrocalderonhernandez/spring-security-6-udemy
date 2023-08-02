package com.debuggeandoideas.app_security.services;

import com.debuggeandoideas.app_security.repositories.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class PartnerRegisteredClientService implements RegisteredClientRepository {

    private PartnerRepository partnerRepository;

    @Override
    public RegisteredClient findByClientId(String clientId) {
       var partnerOpt = this.partnerRepository.findByClientId(clientId);

       return partnerOpt.map(partner -> {
           var authorizationGrantTypes = Arrays.stream(partner.getGrantTypes().split(","))
                   .map(AuthorizationGrantType::new)
                   .toList();

           var clientAuthenticationMethods = Arrays.stream(partner.getAuthenticationMethods().split(","))
                   .map(ClientAuthenticationMethod::new)
                   .toList();

           var scopes = Arrays.stream(partner.getScopes().split(",")).toList();

           return RegisteredClient
                   .withId(partner.getId().toString())
                   .clientId(partner.getClientId())
                   .clientSecret(partner.getClientSecret())
                   .clientName(partner.getClientName())
                   .redirectUri(partner.getRedirectUri())
                   .postLogoutRedirectUri(partner.getRedirectUriLogout())
                   .clientAuthenticationMethod(clientAuthenticationMethods.get(0))
                   .clientAuthenticationMethod(clientAuthenticationMethods.get(1))
                   .scope(scopes.get(0))
                   .scope(scopes.get(1))
                   .authorizationGrantType(authorizationGrantTypes.get(0))
                   .authorizationGrantType(authorizationGrantTypes.get(1))
                   .tokenSettings(this.tokenSettings())
                   .build();
       }).orElseThrow(() -> new BadCredentialsException("Client not exist"));
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    private TokenSettings tokenSettings() {
        return TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofHours(8))
                .build();
    }

}
