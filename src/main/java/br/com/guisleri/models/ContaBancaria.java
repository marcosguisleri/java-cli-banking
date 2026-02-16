package br.com.guisleri.models;

public class ContaBancaria {

    private final String numero;
    private final String titular;
    private double saldo;

    public ContaBancaria(String numero, String titular) {
        if (numero == null || numero.isBlank()) throw new IllegalArgumentException("Número inválido");
        if (titular == null || titular.isBlank()) throw new IllegalArgumentException("Titular inválido");
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            throw new IllegalArgumentException("Valor inválido");
        }
    }

    public boolean sacar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor do saque deve ser > 0");
        if (valor > this.saldo) return false;
        saldo -= valor;
        return true;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
