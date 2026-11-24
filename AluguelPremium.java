package pm_prova2_m;

/**
 * Aluguel premium: quilometragem ilimitada e seguro incluso.
 */
public class AluguelPremium extends Aluguel {

    // O valor total começa em zero e cresce conforme os veículos são
    // associados (ver Aluguel.adicionarVeiculo), por isso não é parâmetro.
    public AluguelPremium(String nomeCliente, int quantidadeDias, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, 0, status);
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return true;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Aluguel Premium");
        exibirDadosComuns();
        System.out.println("  Quilometragem: ilimitada");
    }

    @Override
    public String toString() {
        return "Premium - " + super.toString();
    }
}
