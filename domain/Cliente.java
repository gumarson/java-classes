package br.com.desafio.banco.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Entidade simples para demonstrar Lombok */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private String nome;
    private String documento; // opcional (CPF)
}
