package pm_prova2_m;

/**
 * Van: tem quantidade máxima de passageiros, pode ser avaliada e pode ser
 * inspecionada (implementa as duas interfaces).
 */
public class Van extends Veiculo implements Avaliavel, Inspecionavel {

    private int quantidadeMaximaPassageiros;
    private final RegistroAvaliacoes avaliacoes = new RegistroAvaliacoes();
    private final RegistroInspecoes inspecoes = new RegistroInspecoes();

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int quantidadeMaximaPassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.quantidadeMaximaPassageiros = quantidadeMaximaPassageiros;
    }

    public int getQuantidadeMaximaPassageiros() {
        return quantidadeMaximaPassageiros;
    }

    public void setQuantidadeMaximaPassageiros(int quantidadeMaximaPassageiros) {
        this.quantidadeMaximaPassageiros = quantidadeMaximaPassageiros;
    }

    @Override
    public void avaliar(int nota) {
        avaliacoes.registrar(nota);
    }

    public double getMediaAvaliacoes() {
        return avaliacoes.getMedia();
    }

    @Override
    public void realizarInspecao() {
        inspecoes.registrar(getQuilometragem());
    }

    public int getQuantidadeInspecoes() {
        return inspecoes.getQuantidadeInspecoes();
    }

    // O enunciado não define seguro especial por tipo de veículo. Van leva
    // vários passageiros, então tem seguro especial.
    @Override
    public boolean possuiSeguroEspecial() {
        return true;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Van");
        exibirDadosComuns();
        System.out.println("  Máximo de passageiros: " + quantidadeMaximaPassageiros);
        System.out.println("  Avaliações: " + avaliacoes);
        System.out.println("  Inspeções: " + inspecoes);
    }

    @Override
    public String toString() {
        return "Van " + super.toString();
    }
}
