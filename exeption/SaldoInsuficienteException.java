package br.com.desafio.banco.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(double saldo, double tentativa) {
        super(String.format("Saldo insuficiente. Saldo: R$ %.2f, tentativa de saque/transferência: R$ %.2f",
                saldo, tentativa));
    }
}
