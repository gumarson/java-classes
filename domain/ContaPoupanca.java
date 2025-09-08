package br.com.desafio.banco.domain;

import br.com.desafio.banco.core.Conta;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        imprimirInfoBasica("Extrato Conta Poupança");
    }
}
