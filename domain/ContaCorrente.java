package br.com.desafio.banco.domain;

import br.com.desafio.banco.core.Conta;

public class ContaCorrente extends Conta {
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        imprimirInfoBasica("Extrato Conta Corrente");
    }
}
