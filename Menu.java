package pm_prova2_m;

import java.util.List;
import java.util.Scanner;

/**
 * Interação com o usuário pelo console. Só lê opções, mostra resultados e
 * repassa as ações para a Locadora, que é quem tem as regras.
 */
public class Menu {

    private final Locadora locadora;
    private final Scanner scanner;

    public Menu(Locadora locadora, Scanner scanner) {
        this.locadora = locadora;
        this.scanner = scanner;
    }

    public void executar() {
        int opcao;
        do {
            exibirOpcoes();
            opcao = lerInteiro("Escolha uma opção: ");
            System.out.println();
            switch (opcao) {
                case 1:
                    associarVeiculos();
                    break;
                case 2:
                    exibirVeiculos();
                    break;
                case 3:
                    exibirAlugueis();
                    break;
                case 4:
                    exibirVeiculosDisponiveis();
                    break;
                case 5:
                    avaliar();
                    break;
                case 6:
                    realizarInspecao();
                    break;
                case 7:
                    removerVeiculosEmManutencao();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirOpcoes() {
        System.out.println();
        System.out.println("===== LOCADORA DE VEÍCULOS =====");
        System.out.println("1 - Associar veículos a um aluguel");
        System.out.println("2 - Exibir todos os veículos");
        System.out.println("3 - Exibir todos os aluguéis");
        System.out.println("4 - Exibir veículos disponíveis");
        System.out.println("5 - Avaliar");
        System.out.println("6 - Realizar inspeção");
        System.out.println("7 - Remover veículos em manutenção");
        System.out.println("0 - Sair");
    }

    private void associarVeiculos() {
        List<Aluguel> alugueis = locadora.getAlugueis();
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        System.out.println("Aluguéis:");
        listarNumerado(alugueis);
        int indiceAluguel = lerIndice("Número do aluguel (0 para voltar): ", alugueis.size());
        if (indiceAluguel < 0) {
            return;
        }
        Aluguel aluguel = alugueis.get(indiceAluguel);

        // Repete até o usuário voltar, porque um aluguel pode ter vários veículos.
        while (true) {
            List<Veiculo> disponiveis = locadora.getVeiculosDisponiveis();
            if (disponiveis.isEmpty()) {
                System.out.println("Não há veículos disponíveis para associar.");
                return;
            }
            System.out.println();
            System.out.println("Veículos disponíveis:");
            listarNumerado(disponiveis);
            int indiceVeiculo = lerIndice("Número do veículo (0 para terminar): ", disponiveis.size());
            if (indiceVeiculo < 0) {
                return;
            }
            Veiculo veiculo = disponiveis.get(indiceVeiculo);
            try {
                locadora.associarVeiculo(aluguel, veiculo);
                System.out.printf("Veículo %s associado. Valor total do aluguel: R$ %.2f%n",
                        veiculo.getPlaca(), aluguel.getValorTotal());
            } catch (IllegalStateException e) {
                System.out.println("Não foi possível associar: " + e.getMessage());
                return;
            }
        }
    }

    private void exibirVeiculos() {
        exibirListaVeiculos(locadora.getVeiculos(), "Nenhum veículo cadastrado.");
    }

    private void exibirVeiculosDisponiveis() {
        exibirListaVeiculos(locadora.getVeiculosDisponiveis(), "Nenhum veículo disponível.");
    }

    private void exibirListaVeiculos(List<Veiculo> veiculos, String mensagemVazia) {
        if (veiculos.isEmpty()) {
            System.out.println(mensagemVazia);
            return;
        }
        for (Veiculo veiculo : veiculos) {
            veiculo.exibirDetalhes();
            System.out.println();
        }
    }

    private void exibirAlugueis() {
        List<Aluguel> alugueis = locadora.getAlugueis();
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        for (Aluguel aluguel : alugueis) {
            aluguel.exibirDetalhes();
            System.out.println();
        }
    }

    private void avaliar() {
        List<Avaliavel> avaliaveis = locadora.getAvaliaveis();
        if (avaliaveis.isEmpty()) {
            System.out.println("Nada para avaliar.");
            return;
        }
        System.out.println("Itens que podem ser avaliados:");
        listarNumerado(avaliaveis);
        int indice = lerIndice("Número do item (0 para voltar): ", avaliaveis.size());
        if (indice < 0) {
            return;
        }
        int nota = lerInteiro("Nota (" + RegistroAvaliacoes.NOTA_MINIMA + " a "
                + RegistroAvaliacoes.NOTA_MAXIMA + "): ");
        try {
            avaliaveis.get(indice).avaliar(nota);
            System.out.println("Avaliação registrada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Avaliação não registrada: " + e.getMessage());
        }
    }

    private void realizarInspecao() {
        List<Inspecionavel> inspecionaveis = locadora.getInspecionaveis();
        if (inspecionaveis.isEmpty()) {
            System.out.println("Nenhum veículo que pode ser inspecionado.");
            return;
        }
        System.out.println("Veículos que podem ser inspecionados:");
        listarNumerado(inspecionaveis);
        int indice = lerIndice("Número do veículo (0 para voltar): ", inspecionaveis.size());
        if (indice < 0) {
            return;
        }
        inspecionaveis.get(indice).realizarInspecao();
        System.out.println("Inspeção registrada.");
    }

    private void removerVeiculosEmManutencao() {
        List<Veiculo> removidos = locadora.removerVeiculosEmManutencao();
        if (removidos.isEmpty()) {
            System.out.println("Nenhum veículo em manutenção.");
            return;
        }
        System.out.println(removidos.size() + " veículo(s) removido(s):");
        for (Veiculo veiculo : removidos) {
            System.out.println("  - " + veiculo);
        }
    }

    private void listarNumerado(List<?> itens) {
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + " - " + itens.get(i));
        }
    }

    /**
     * Lê um número entre 1 e o tamanho da lista e devolve o índice dela
     * (base 0), ou -1 se o usuário digitar 0 para voltar.
     */
    private int lerIndice(String mensagem, int tamanho) {
        while (true) {
            int numero = lerInteiro(mensagem);
            if (numero >= 0 && numero <= tamanho) {
                return numero - 1;
            }
            System.out.println("Digite um número entre 0 e " + tamanho + ".");
        }
    }

    // Lê a linha inteira e converte, em vez de usar nextInt(): assim um texto
    // digitado por engano não derruba o programa nem fica preso no buffer.
    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, digite um número inteiro.");
            }
        }
    }
}
