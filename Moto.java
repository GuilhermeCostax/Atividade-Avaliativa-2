package pm_prova2_m;

/**
 * Moto: tem cilindrada, pode ser inspecionada e não recebe avaliação (por isso
 * não implementa Avaliavel).
 */
public class Moto extends Veiculo implements Inspecionavel {

    private int cilindrada;
    private final RegistroInspecoes inspecoes = new RegistroInspecoes();

    public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria,
            double quilometragem, StatusVeiculo status, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void realizarInspecao() {
        inspecoes.registrar(getQuilometragem());
    }

    public int getQuantidadeInspecoes() {
        return inspecoes.getQuantidadeInspecoes();
    }

    // O enunciado não define seguro especial por tipo de veículo. Moto tem
    // risco de acidente maior que carro, então tem seguro especial.
    @Override
    public boolean possuiSeguroEspecial() {
        return true;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Moto");
        exibirDadosComuns();
        System.out.println("  Cilindrada: " + cilindrada + " cc");
        System.out.println("  Inspeções: " + inspecoes);
    }

    @Override
    public String toString() {
        return "Moto " + super.toString();
    }
}
