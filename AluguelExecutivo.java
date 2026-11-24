package pm_prova2_m;

/**
 * Aluguel executivo: quilometragem ilimitada, motorista particular e pode ser
 * avaliado (implementa Avaliavel, assim como Carro e Van).
 */
public class AluguelExecutivo extends Aluguel implements Avaliavel {

    private String nomeMotorista;
    private final RegistroAvaliacoes avaliacoes = new RegistroAvaliacoes();

    // O valor total começa em zero e cresce conforme os veículos são
    // associados (ver Aluguel.adicionarVeiculo), por isso não é parâmetro.
    public AluguelExecutivo(String nomeCliente, int quantidadeDias, StatusAluguel status,
            String nomeMotorista) {
        super(nomeCliente, quantidadeDias, 0, status);
        this.nomeMotorista = nomeMotorista;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    @Override
    public void avaliar(int nota) {
        avaliacoes.registrar(nota);
    }

    public double getMediaAvaliacoes() {
        return avaliacoes.getMedia();
    }

    // O enunciado só cita seguro incluso no Premium; no Executivo ele lista
    // quilometragem ilimitada, motorista e avaliação, sem seguro.
    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Aluguel Executivo");
        exibirDadosComuns();
        System.out.println("  Quilometragem: ilimitada");
        System.out.println("  Motorista particular: " + nomeMotorista);
        System.out.println("  Avaliações: " + avaliacoes);
    }

    @Override
    public String toString() {
        return "Executivo - " + super.toString();
    }
}
