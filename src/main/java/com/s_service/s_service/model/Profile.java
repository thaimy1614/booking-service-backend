package com.s_service.s_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private String phone;

    private String address;
}
