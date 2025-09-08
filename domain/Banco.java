package br.com.desafio.banco.domain;

import br.com.desafio.banco.core.Conta;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Banco {
    private final String nome;
    private final List<Conta> contas = new ArrayList<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void listarContas() {
        System.out.println("=== Contas do banco " + nome + " ===");
        contas.forEach(c ->
            System.out.println(c.getCliente().getNome() + " - Ag " + c.getAgencia() + " / Nr " + c.getNumero())
        );
    }
}
