package com.debuggeandoideas.app_security.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "partners")
@Data
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String clientId;
    private String clientName;
    private String clientSecret;
    private String scopes;
    private String grantTypes;
    private String authenticationMethods;
    private String redirectUri;
    private String redirectUriLogout;
}
