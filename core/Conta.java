package br.com.desafio.banco.core;

import br.com.desafio.banco.domain.Cliente;
import br.com.desafio.banco.exception.SaldoInsuficienteException;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(of = {"agencia", "numero", "saldo"})
public abstract class Conta implements IConta {

    protected static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected final int agencia;
    protected final int numero;
    protected double saldo;
    protected final Cliente cliente;

    protected Conta(@NonNull Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    @Override
    public void sacar(double valor) {
        validarValorPositivo(valor);
        if (saldo < valor) throw new SaldoInsuficienteException(saldo, valor);
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        validarValorPositivo(valor);
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta destino) {
        sacar(valor);
        destino.depositar(valor);
    }

    protected void imprimirInfoBasica(String titulo) {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Agência: " + agencia);
        System.out.println("Número : " + numero);
        System.out.printf ("Saldo  : R$ %.2f%n", saldo);
    }

    private void validarValorPositivo(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo.");
        }
    }
}
