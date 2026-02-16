package br.com.guisleri.models;

public class Banco {

    private final ContaBancaria[] contas;
    private int total;

    public Banco() {
        this.contas = new ContaBancaria[10];
        this.total = 0;
    }

    public void adicionarConta(ContaBancaria conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser null");
        }

        if (this.total >= this.contas.length) {
            throw new IllegalStateException("Banco cheio! Limite: " + contas.length);
        }

        if (buscarPorNumero(conta.getNumero()) != null) {
            throw new IllegalArgumentException("Já existe uma conta com número: " + conta.getNumero());
        }

        contas[total] = conta;
        total++;
    }

    public ContaBancaria[] listarContas() {
        ContaBancaria[] copia = new ContaBancaria[total];
        for (int i = 0; i < total; i++) {
            copia[i] = contas[i];
        }
        return copia;
    }

    public ContaBancaria buscarPorNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número inválido!");
        }

        for (int i = 0; i < total; i++) {
            if (contas[i].getNumero().equals(numero)) {
                return contas[i];
            }
        }
        return null;
    }

}
