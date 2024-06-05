package com.example.spring_into.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String lastName;

    private String firstName;

    private String address;

    @OneToMany(mappedBy = "owner")
    private Set<TollPass> tollPass;
}
