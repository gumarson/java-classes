package br.com.desafio.banco.core;

public interface IConta {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, IConta destino);
    void imprimirExtrato();
}
