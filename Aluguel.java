/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pm_prova2_m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author glbra
 */
public abstract class Aluguel {

    private String nomeCliente;
    private int quantidadeDias;
    private double valorTotal;
    private StatusAluguel status;
    // O enunciado diz que cada aluguel pode ter vários veículos, mas a classe
    // recebida não tinha onde guardá-los. Fica aqui, e não nas subclasses,
    // porque vale para todo tipo de aluguel.
    private final List<Veiculo> veiculos = new ArrayList<>();

    public Aluguel(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {

        this.nomeCliente = nomeCliente;
        this.quantidadeDias = quantidadeDias;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusAluguel getStatus() {
        return status;
    }

    public void setStatus(StatusAluguel status) {
        this.status = status;
    }

    public abstract void exibirDetalhes();

    public abstract boolean possuiSeguroIncluso();

    /**
     * Devolve a lista sem permitir alteração: veículo só entra pelo
     * adicionarVeiculo(), que confere o status e atualiza o valor total.
     */
    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        if (status != StatusAluguel.ATIVO) {
            throw new IllegalStateException("Só é possível associar veículos a um aluguel ATIVO.");
        }
        // Veículo alugado ou em manutenção não está no pátio. Sem essa
        // checagem o mesmo carro poderia ir para dois clientes ao mesmo tempo.
        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
            throw new IllegalStateException("O veículo " + veiculo.getPlaca()
                    + " não está disponível (status " + veiculo.getStatus() + ").");
        }
        veiculos.add(veiculo);
        veiculo.setStatus(StatusVeiculo.ALUGADO);
        valorTotal += veiculo.getValorDiaria() * quantidadeDias;
    }

    /**
     * Imprime os dados que todo aluguel tem. Cada subclasse chama este método
     * no seu exibirDetalhes() e depois imprime só o que é dela.
     */
    protected void exibirDadosComuns() {
        System.out.println("  Cliente: " + nomeCliente);
        System.out.println("  Quantidade de dias: " + quantidadeDias);
        System.out.println("  Status: " + status);
        System.out.println("  Seguro incluso: " + (possuiSeguroIncluso() ? "sim" : "não"));
        System.out.printf("  Valor total: R$ %.2f%n", valorTotal);
        if (veiculos.isEmpty()) {
            System.out.println("  Veículos: nenhum associado");
        } else {
            System.out.println("  Veículos:");
            for (Veiculo veiculo : veiculos) {
                System.out.println("    - " + veiculo);
            }
        }
    }

    @Override
    public String toString() {
        return nomeCliente + ", " + quantidadeDias + " dia(s) (" + status + ")";
    }

}
