package com.example.hexagonalarchitecture.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String code;
    private byte age;
    private String country;
    private Boolean verified;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public User() {
        this.code = UUID.randomUUID().toString();
        this.verified = false;
    }

}
