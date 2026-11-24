package pm_prova2_m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Regras do sistema: guarda os veículos e aluguéis cadastrados e executa as
 * funcionalidades sobre eles. Não lê nem imprime nada; a conversa com o
 * usuário fica no Menu, para as regras poderem ser usadas sem o console.
 */
public class Locadora {

    private final List<Veiculo> veiculos = new ArrayList<>();
    private final List<Aluguel> alugueis = new ArrayList<>();

    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void cadastrarAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    public List<Aluguel> getAlugueis() {
        return Collections.unmodifiableList(alugueis);
    }

    public void associarVeiculo(Aluguel aluguel, Veiculo veiculo) {
        aluguel.adicionarVeiculo(veiculo);
    }

    public List<Veiculo> getVeiculosDisponiveis() {
        List<Veiculo> disponiveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatus() == StatusVeiculo.DISPONIVEL) {
                disponiveis.add(veiculo);
            }
        }
        return disponiveis;
    }

    /**
     * Junta veículos e aluguéis avaliáveis numa lista só: o que importa para
     * avaliar é a interface, não se o objeto é veículo ou aluguel.
     */
    public List<Avaliavel> getAvaliaveis() {
        List<Avaliavel> avaliaveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) veiculo);
            }
        }
        for (Aluguel aluguel : alugueis) {
            if (aluguel instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) aluguel);
            }
        }
        return avaliaveis;
    }

    public List<Inspecionavel> getInspecionaveis() {
        List<Inspecionavel> inspecionaveis = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Inspecionavel) {
                inspecionaveis.add((Inspecionavel) veiculo);
            }
        }
        return inspecionaveis;
    }

    /**
     * Remove do cadastro todos os veículos em manutenção.
     *
     * @return os veículos removidos, para o menu mostrar quais saíram
     */
    public List<Veiculo> removerVeiculosEmManutencao() {
        List<Veiculo> removidos = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatus() == StatusVeiculo.MANUTENCAO) {
                removidos.add(veiculo);
            }
        }
        veiculos.removeAll(removidos);
        return removidos;
    }
}
