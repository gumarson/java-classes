package br.com.desafio.banco;

import br.com.desafio.banco.core.IConta;
import br.com.desafio.banco.domain.*;

public class App {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital DIO");

        Cliente maria = Cliente.builder().nome("Maria Silva").documento("111.222.333-44").build();
        Cliente joao  = Cliente.builder().nome("João Souza").documento("555.666.777-88").build();

        IConta ccMaria = new ContaCorrente(maria);
        IConta poupJoao = new ContaPoupanca(joao);

        banco.adicionarConta((ContaCorrente) ccMaria);
        banco.adicionarConta((ContaPoupanca) poupJoao);

        // Operações básicas
        ccMaria.depositar(1000);
        ccMaria.sacar(250);
        ccMaria.transferir(200, poupJoao);

        // Extratos
        ccMaria.imprimirExtrato();
        poupJoao.imprimirExtrato();

        // Listagem do banco
        banco.listarContas();
    }
}
