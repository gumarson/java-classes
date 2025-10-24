package com.example.academia.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String bairro;
    private LocalDate dataDeNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<AvaliacaoFisica> avaliacoesFisica;
}
