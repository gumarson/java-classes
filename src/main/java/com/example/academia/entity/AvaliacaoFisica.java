package com.example.academia.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class AvaliacaoFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    private LocalDateTime dataDaAvaliacao = LocalDateTime.now();
    private double peso;
    private double altura;
}
