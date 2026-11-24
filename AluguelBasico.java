package pm_prova2_m;

/**
 * Aluguel básico: permite até 100 km por dia e não tem seguro incluso.
 */
public class AluguelBasico extends Aluguel {

    public static final int LIMITE_KM_POR_DIA = 100;

    // O valor total começa em zero e cresce conforme os veículos são
    // associados (ver Aluguel.adicionarVeiculo), por isso não é parâmetro.
    public AluguelBasico(String nomeCliente, int quantidadeDias, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, 0, status);
    }

    /** Quilometragem total permitida no período inteiro do aluguel. */
    public int getLimiteKmTotal() {
        return LIMITE_KM_POR_DIA * getQuantidadeDias();
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Aluguel Básico");
        exibirDadosComuns();
        System.out.println("  Quilometragem: até " + LIMITE_KM_POR_DIA + " km por dia ("
                + getLimiteKmTotal() + " km no período)");
    }

    @Override
    public String toString() {
        return "Básico - " + super.toString();
    }
}
