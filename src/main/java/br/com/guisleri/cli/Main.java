package br.com.guisleri.cli;

import br.com.guisleri.models.Banco;
import br.com.guisleri.models.ContaBancaria;

public class Main {

    void main() {

        Banco banco = new Banco();

        while (true) {

            titulo("🏦 JAVA CLI BANKING");

            menu(
                    "1) ➕ Adicionar conta",
                    "2) 🎯 Selecionar conta",
                    "3) 🚪 Sair"
            );

            int opcao = lerInt("Escolha uma opção: ");
            pular();

            if (opcao == -1) {
                IO.println("❌ Entrada inválida. Digite um número.");
                pular();
                continue;
            }

            if (opcao < 1 || opcao > 3) {
                IO.println("❌ Opção inválida.");
                pular();
                continue;
            }

            if (opcao == 1) {
                criarConta(banco);
            } else if (opcao == 2) {
                selecionarConta(banco);
            } else {
                IO.println("👋 Até mais!");
                pular();
                break;
            }
        }
    }

    private void criarConta(Banco banco) {
        titulo("➕ Criar nova conta");

        int numeroInt = (int) (Math.random() * 999) + 1;
        String numero = String.format("%03d", numeroInt);

        String titular = IO.readln("Titular da conta: ");

        try {
            ContaBancaria conta = new ContaBancaria(numero, titular);
            banco.adicionarConta(conta);

            IO.println("Conta criada com sucesso!");
            IO.println("   Número : " + numero);
            IO.println("   Titular: " + titular);
            pular();

        } catch (RuntimeException e) {
            IO.println("Não foi possível criar a conta: " + e.getMessage());
            pular();
        }
    }

    private void selecionarConta(Banco banco) {
        listarContas(banco);

        ContaBancaria[] contas = banco.listarContas();
        if (contas.length == 0) {
            return;
        }

        String numero = IO.readln("Digite o número da conta: ");

        ContaBancaria achada;
        try {
            achada = banco.buscarPorNumero(numero);
        } catch (RuntimeException e) {
            IO.println("Busca falhou: " + e.getMessage());
            pular();
            return;
        }

        if (achada == null) {
            IO.println("Conta não encontrada.");
            pular();
            return;
        }

        IO.println("Conta selecionada: " + achada.getNumero() + " — " + achada.getTitular());
        pular();

        menuConta(banco, achada);
    }

    private void menuConta(Banco banco, ContaBancaria conta) {
        while (true) {

            titulo("👤 Conta selecionada");
            IO.println("Número : " + conta.getNumero());
            IO.println("Titular: " + conta.getTitular());
            IO.println("Saldo  : R$ " + conta.getSaldo());
            pular();

            menu(
                    "1) 💰 Depositar",
                    "2) 🏧 Sacar",
                    "3) 📊 Consultar saldo",
                    "4) 🔁 Transferir",
                    "5) ↩️  Voltar"
            );

            int op = lerInt("Escolha uma opção: ");
            pular();

            if (op == -1) {
                IO.println("Entrada inválida. Digite um número.");
                pular();
                continue;
            }

            if (op < 1 || op > 5) {
                IO.println("Opção inválida.");
                pular();
                continue;
            }

            if (op == 1) {
                depositar(conta);
            } else if (op == 2) {
                sacar(conta);
            } else if (op == 3) {
                IO.println("📌 Saldo Atual: R$ " + conta.getSaldo());
                pular();
            } else if (op == 4) {
                transferir(banco, conta);
            } else {
                IO.println("↩️ Voltando ao menu principal...");
                pular();
                break;
            }
        }
    }

    private void listarContas(Banco banco) {
        ContaBancaria[] contas = banco.listarContas();

        titulo("📋 Contas cadastradas");
        if (contas.length == 0) {
            IO.println("ℹ️  Nenhuma conta cadastrada.");
            pular();
            return;
        }

        for (ContaBancaria c : contas) {
            IO.println("• " + c.getNumero() + " — " + c.getTitular());
        }
        pular();
    }

    private void depositar(ContaBancaria conta) {
        titulo("💰 Depósito");

        double valor = lerDouble("Valor do depósito (R$): ");
        if (Double.isNaN(valor)) {
            IO.println("Digite um valor numérico válido.");
            pular();
            return;
        }

        try {
            conta.depositar(valor);
            IO.println("Depósito realizado: R$ " + valor);
            IO.println("Saldo atual: R$ " + conta.getSaldo());
            pular();
        } catch (RuntimeException e) {
            IO.println("Depósito falhou: " + e.getMessage());
            pular();
        }
    }

    private void sacar(ContaBancaria conta) {
        titulo("🏧 Saque");

        double valor = lerDouble("Valor do saque (R$): ");
        if (Double.isNaN(valor)) {
            IO.println("Digite um valor numérico válido.");
            pular();
            return;
        }

        try {
            boolean ok = conta.sacar(valor);
            if (ok) {
                IO.println("Saque realizado: R$ " + valor);
                IO.println("📌 Saldo atual: R$ " + conta.getSaldo());
            } else {
                IO.println("Saldo insuficiente para sacar R$ " + valor);
            }
            pular();
        } catch (RuntimeException e) {
            IO.println("Saque falhou: " + e.getMessage());
            pular();
        }
    }

    private void transferir(Banco banco, ContaBancaria origem) {
        titulo("🔁 Transferência");

        IO.println("Origem: " + origem.getNumero() + " — " + origem.getTitular());
        pular();

        String destinoNumero = IO.readln("Destino (número): ");

        double valor = lerDouble("Valor (R$): ");
        if (Double.isNaN(valor)) {
            IO.println("Digite um valor numérico válido.");
            pular();
            return;
        }

        try {
            boolean ok = banco.transferirValores(origem.getNumero(), destinoNumero, valor);
            if (ok) {
                IO.println("Transferência realizada: R$ " + valor);
                IO.println("Saldo atual (origem): R$ " + origem.getSaldo());
            } else {
                IO.println("Saldo insuficiente para transferir R$ " + valor);
            }
            pular();
        } catch (RuntimeException e) {
            IO.println("Transferência falhou: " + e.getMessage());
            pular();
        }
    }

    private int lerInt(String prompt) {
        try {
            return Integer.parseInt(IO.readln(prompt));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double lerDouble(String prompt) {
        try {
            return Double.parseDouble(IO.readln(prompt));
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private void pular() {
        IO.println("");
    }

    private void linha() {
        IO.println("----------------------------------------");
    }

    private void titulo(String t) {
        linha();
        IO.println(t);
        linha();
    }

    private void menu(String... itens) {
        for (String item : itens) {
            IO.println(item);
        }
        linha();
    }
}