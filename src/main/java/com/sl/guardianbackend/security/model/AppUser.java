package com.sl.guardianbackend.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    //dla celów testowych dodajemy kolumnę z odkodowanym hasłem
    //TODO - po testach usunąć!!!!!
    private String noEncodingPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}