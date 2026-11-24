package pm_prova2_m;

/**
 * Guarda as notas recebidas por um objeto avaliável.
 *
 * Carro, Van e AluguelExecutivo são avaliáveis, mas não têm uma superclasse
 * comum que só eles herdam. Em vez de repetir a mesma lógica de notas nas três
 * classes, cada uma tem um RegistroAvaliacoes e delega a ele (composição).
 */
public class RegistroAvaliacoes {

    public static final int NOTA_MINIMA = 0;
    public static final int NOTA_MAXIMA = 10;

    private int somaNotas;
    private int quantidadeAvaliacoes;

    public void registrar(int nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(
                    "A nota deve estar entre " + NOTA_MINIMA + " e " + NOTA_MAXIMA + ".");
        }
        somaNotas += nota;
        quantidadeAvaliacoes++;
    }

    public int getQuantidadeAvaliacoes() {
        return quantidadeAvaliacoes;
    }

    public double getMedia() {
        // Sem avaliações a média é 0, e não divisão por zero.
        if (quantidadeAvaliacoes == 0) {
            return 0;
        }
        return (double) somaNotas / quantidadeAvaliacoes;
    }

    @Override
    public String toString() {
        if (quantidadeAvaliacoes == 0) {
            return "sem avaliações";
        }
        return String.format("média %.1f (%d avaliação(ões))", getMedia(), quantidadeAvaliacoes);
    }
}
