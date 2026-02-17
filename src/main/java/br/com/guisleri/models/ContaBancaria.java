package br.com.guisleri.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

    private final String numero;
    private final String titular;
    private double saldo;
    private final List<String> extrato = new ArrayList<>();

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
        adicionarExtrato("DEPOSITO +" + valor);
    }

    public boolean sacar(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor do saque deve ser > 0");
        if (valor > this.saldo) return false;
        this.saldo -= valor;
        adicionarExtrato("SAQUE -" + valor);
        return true;
    }

    private void adicionarExtrato(String mensagem) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = agora.format(formatter);
        extrato.add(dataFormatada + " " + mensagem + " | Saldo: " + this.saldo);
    }

    public void registrarTransferenciaEnviada(String numeroDestino, double valor) {
        adicionarExtrato("TRANSFERENCIA ENVIADA -" + valor + " -> " + numeroDestino);
    }

    public void registrarTransferenciaRecebida(String numeroOrigem, double valor) {
        adicionarExtrato("TRANSFERENCIA RECEBIDA +" + valor + " <- " + numeroOrigem);
    }

    public List<String> getExtrato() { return new ArrayList<>(extrato); }

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
