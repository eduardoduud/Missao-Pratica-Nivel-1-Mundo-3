import model.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("==================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Persistir dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Incluir Pessoa:");
                    System.out.println("1 - Pessoa Física");
                    System.out.println("2 - Pessoa Jurídica");
                    System.out.print("Escolha o tipo (1 ou 2): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1) {
                        incluirPessoaFisica(repoFisica, scanner);
                    } else if (tipo == 2) {
                        incluirPessoaJuridica(repoJuridica, scanner);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 2:
                    System.out.println("Alterar Pessoa:");
                    System.out.println("1 - Pessoa Física");
                    System.out.println("2 - Pessoa Jurídica");
                    System.out.print("Escolha o tipo (1 ou 2): ");
                    int tipoAlterar = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoAlterar == 1) {
                        alterarPessoaFisica(repoFisica, scanner);
                    } else if (tipoAlterar == 2) {
                        alterarPessoaJuridica(repoJuridica, scanner);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 3:
                    System.out.println("Excluir Pessoa:");
                    System.out.println("1 - Pessoa Física");
                    System.out.println("2 - Pessoa Jurídica");
                    System.out.print("Escolha o tipo (1 ou 2): ");
                    int tipoExcluir = scanner.nextInt();
                    System.out.print("Digite o ID: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoExcluir == 1) {
                        repoFisica.excluir(idExcluir);
                    } else if (tipoExcluir == 2) {
                        repoJuridica.excluir(idExcluir);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 4:
                    System.out.println("Exibir pelo ID:");
                    System.out.println("1 - Pessoa Física");
                    System.out.println("2 - Pessoa Jurídica");
                    System.out.print("Escolha o tipo (1 ou 2): ");
                    int tipoExibir = scanner.nextInt();
                    System.out.print("Digite o ID: ");
                    int idExibir = scanner.nextInt();
                    scanner.nextLine();
                    if (tipoExibir == 1) {
                        PessoaFisica pessoaFisica = repoFisica.obter(idExibir);
                        if (pessoaFisica != null) {
                            pessoaFisica.exibir();
                        } else {
                            System.out.println("Pessoa física não encontrada.");
                        }
                    } else if (tipoExibir == 2) {
                        PessoaJuridica pessoaJuridica = repoJuridica.obter(idExibir);
                        if (pessoaJuridica != null) {
                            pessoaJuridica.exibir();
                        } else {
                            System.out.println("Pessoa jurídica não encontrada.");
                        }
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;
                case 5:
                    System.out.println("Exibir Todos:");
                    System.out.println("1 - Pessoa Física");
                    System.out.println("2 - Pessoa Jurídica");
                    System.out.print("Escolha o tipo (1 ou 2): ");
                    int tipoExibirTodos = scanner.nextInt();
                    if (tipoExibirTodos == 1) {
                        System.out.println("Pessoas Físicas:");
                        for (PessoaFisica pessoa : repoFisica.obterTodos()) {
                            pessoa.exibir();
                        }
                    } else if (tipoExibirTodos == 2) {
                        System.out.println("Pessoas Jurídicas:");
                        for (PessoaJuridica pessoa : repoJuridica.obterTodos()) {
                            pessoa.exibir();
                        }
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;
                case 6:
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixo = scanner.nextLine();
                    try {
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoRecuperar = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixoRecuperar + ".fisica.bin");
                        repoJuridica.recuperar(prefixoRecuperar + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Finalizando execução...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void incluirPessoaFisica(PessoaFisicaRepo repo, Scanner scanner) {
        System.out.print("Digite o ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        PessoaFisica pessoa = new PessoaFisica(id, nome, cpf, idade);
        repo.inserir(pessoa);
        System.out.println("Pessoa física adicionada com sucesso.");
    }

    private static void incluirPessoaJuridica(PessoaJuridicaRepo repo, Scanner scanner) {
        System.out.print("Digite o ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.nextLine();

        PessoaJuridica pessoa = new PessoaJuridica(id, nome, cnpj);
        repo.inserir(pessoa);
        System.out.println("Pessoa jurídica adicionada com sucesso.");
    }

    private static void alterarPessoaFisica(PessoaFisicaRepo repo, Scanner scanner) {
        System.out.print("Digite o ID da pessoa física a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaFisica pessoa = repo.obter(id);
        if (pessoa != null) {
            System.out.println("Dados atuais da pessoa física:");
            pessoa.exibir();
            System.out.println("Digite os novos dados:");
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setIdade(idade);
            System.out.println("Pessoa física alterada com sucesso.");
        } else {
            System.out.println("Pessoa física não encontrada.");
        }
    }

    private static void alterarPessoaJuridica(PessoaJuridicaRepo repo, Scanner scanner) {
        System.out.print("Digite o ID da pessoa jurídica a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaJuridica pessoa = repo.obter(id);
        if (pessoa != null) {
            System.out.println("Dados atuais da pessoa jurídica:");
            pessoa.exibir();
            System.out.println("Digite os novos dados:");
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CNPJ: ");
            String cnpj = scanner.nextLine();

            pessoa.setNome(nome);
            pessoa.setCnpj(cnpj);
            System.out.println("Pessoa jurídica alterada com sucesso.");
        } else {
            System.out.println("Pessoa jurídica não encontrada.");
        }
    }
}