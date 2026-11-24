package pm_prova2_m;

/**
 * Carro: tem quantidade de portas, pode ser avaliado e não precisa de inspeção
 * (por isso não implementa Inspecionavel).
 */
public class Carro extends Veiculo implements Avaliavel {

    private int quantidadePortas;
    private final RegistroAvaliacoes avaliacoes = new RegistroAvaliacoes();

    public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadePortas = quantidadePortas;
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public void avaliar(int nota) {
        avaliacoes.registrar(nota);
    }

    public double getMediaAvaliacoes() {
        return avaliacoes.getMedia();
    }

    // O enunciado não define seguro especial por tipo de veículo. Carro é o
    // veículo de menor risco da frota, então fica sem seguro especial.
    @Override
    public boolean possuiSeguroEspecial() {
        return false;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Carro");
        exibirDadosComuns();
        System.out.println("  Quantidade de portas: " + quantidadePortas);
        System.out.println("  Avaliações: " + avaliacoes);
    }

    @Override
    public String toString() {
        return "Carro " + super.toString();
    }
}
