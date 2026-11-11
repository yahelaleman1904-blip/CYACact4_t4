package com.cyac.act4_t4.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // Lombok genera los getters y setters automáticamente
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}