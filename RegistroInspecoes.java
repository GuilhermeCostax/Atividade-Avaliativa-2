package pm_prova2_m;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Guarda o histórico de inspeções de um veículo inspecionável.
 *
 * Moto e Van são inspecionáveis e Carro não, então essa lógica não pode ficar
 * em Veiculo. Fica aqui, usada por composição, para não ser repetida nas duas
 * classes.
 */
public class RegistroInspecoes {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int quantidadeInspecoes;
    private LocalDate dataUltimaInspecao;
    private double quilometragemUltimaInspecao;

    public void registrar(double quilometragemAtual) {
        quantidadeInspecoes++;
        dataUltimaInspecao = LocalDate.now();
        quilometragemUltimaInspecao = quilometragemAtual;
    }

    public int getQuantidadeInspecoes() {
        return quantidadeInspecoes;
    }

    public LocalDate getDataUltimaInspecao() {
        return dataUltimaInspecao;
    }

    public double getQuilometragemUltimaInspecao() {
        return quilometragemUltimaInspecao;
    }

    @Override
    public String toString() {
        if (quantidadeInspecoes == 0) {
            return "nunca inspecionado";
        }
        return String.format("%d inspeção(ões), última em %s com %.1f km",
                quantidadeInspecoes, dataUltimaInspecao.format(FORMATO_DATA), quilometragemUltimaInspecao);
    }
}
