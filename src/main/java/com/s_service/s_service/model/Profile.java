package com.s_service.s_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    private String id;

    private String name;

    private String email;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Account.AccountStatus status;
}
