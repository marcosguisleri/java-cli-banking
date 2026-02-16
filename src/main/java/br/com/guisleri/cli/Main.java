package br.com.guisleri.cli;

import br.com.guisleri.models.Banco;
import br.com.guisleri.models.ContaBancaria;

public class Main {

    void main() {

        Banco banco = new Banco();

        while (true) {
            IO.println("==================");
            IO.println("1 - Adicionar conta");
            IO.println("2 - Selecionar conta");
            IO.println("3 - Sair");
            IO.println("==================");

            int opcao;
            try {
                opcao = Integer.parseInt(IO.readln("Informe a opção desejada: "));
            } catch (NumberFormatException e) {
                IO.println("Digite um número válido.\n");
                continue;
            }

            IO.println();

            if (opcao < 1 || opcao > 3) {
                IO.println("Opção inválida.\n");
                continue;
            }

            if (opcao == 1) {
                int numeroInt = (int) (Math.random() * 999) + 1;
                String numero = String.format("%03d", numeroInt);

                String titular = IO.readln("Informe o titular da conta: ");
                try {
                    ContaBancaria conta = new ContaBancaria(numero, titular);
                    banco.adicionarConta(conta);
                    IO.println("Conta adicionada com sucesso! Número: " + numero + "\n");
                } catch (RuntimeException e) {
                    IO.println("Falha ao criar/adicionar conta: " + e.getMessage() + "\n");
                }

            } else if (opcao == 2) {

                ContaBancaria[] contas = banco.listarContas();
                if (contas.length == 0) {
                    IO.println("Nenhuma conta cadastrada.\n");
                } else {
                    for (ContaBancaria c : contas) {
                        IO.println("Número: " + c.getNumero());
                        IO.println("Nome Titular: " + c.getTitular());
                        IO.println();
                    }
                }

                String numero = IO.readln("Digite o número da conta para selecionar: ");
                ContaBancaria achada = banco.buscarPorNumero(numero);

                if (achada == null) {
                    IO.println("\nConta não encontrada.\n");
                    continue;
                }

                IO.println("\nConta selecionada com sucesso!\n");

                while (true) {
                    IO.println("Número: " + achada.getNumero());
                    IO.println("Nome Titular: " + achada.getTitular());

                    IO.println("==================");
                    IO.println("1 - Depositar");
                    IO.println("2 - Sacar");
                    IO.println("3 - Consultar Saldo");
                    IO.println("4 - Cancelar");
                    IO.println("==================");

                    int op2 = Integer.parseInt(IO.readln("Informe a opção desejada: "));

                    if (op2 < 1 || op2 > 4) {
                        IO.println("Opção inválida.\n");
                        continue;
                    }

                    if (op2 == 1) {
                        try {
                            double valor = Double.parseDouble(IO.readln("Informe o valor que deseja depositar: R$ "));
                            achada.depositar(valor);
                            IO.println("Depósito realizado!\n");
                        } catch (NumberFormatException e) {
                            IO.println("Digite um número válido.\n");
                        } catch (RuntimeException e) {
                            IO.println("Depósito falhou: " + e.getMessage() + "\n");
                        }

                    } else if (op2 == 2) {
                        try {
                            double valor = Double.parseDouble(IO.readln("Informe o valor que deseja sacar: R$ "));
                            boolean ok = achada.sacar(valor);
                            IO.println(ok ? "Saque realizado!\n" : "Saldo insuficiente.\n");
                        } catch (NumberFormatException e) {
                            IO.println("Digite um número válido.\n");
                        } catch (RuntimeException e) {
                            IO.println("Saque falhou: " + e.getMessage() + "\n");
                        }

                    } else if (op2 == 3) {
                        IO.println("Saldo Atual: R$ " + achada.getSaldo() + "\n");

                    } else {
                        IO.println("Voltando ao menu principal...\n");
                        break;
                    }
                }

            } else {
                break;
            }
        }

    }

}
