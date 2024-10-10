package com.s_service.s_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;

    @Column(name = "benefits")
    @Convert(converter = JsonbConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private List<String> benefits;

    @OneToMany(mappedBy = "category", targetEntity = Service.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Service> services;

    public enum CategoryStatus{
        AVAILABLE, DELETED
    }
}
