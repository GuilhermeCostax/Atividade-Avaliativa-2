/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pm_prova2_m;

import java.util.Scanner;

/**
 *
 * @author glbra
 */
public class PM_Prova2_M {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locadora locadora = new Locadora();
        criarDadosIniciais(locadora);

        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(locadora, scanner);
        menu.executar();
        scanner.close();
    }

    /**
     * Cria 3 veículos e 2 aluguéis assim que o programa inicia, como pede o
     * enunciado. A Van começa em manutenção para a opção de remover veículos
     * em manutenção ter o que mostrar.
     */
    private static void criarDadosIniciais(Locadora locadora) {
        locadora.cadastrarVeiculo(new Carro("ABC1D23", "Chevrolet Onix", 2022, 150.0, 35000.0,
                StatusVeiculo.DISPONIVEL, 4));
        locadora.cadastrarVeiculo(new Moto("XYZ9K87", "Honda CB 500", 2021, 120.0, 18000.0,
                StatusVeiculo.DISPONIVEL, 500));
        locadora.cadastrarVeiculo(new Van("QWE4R56", "Mercedes Sprinter", 2020, 350.0, 80000.0,
                StatusVeiculo.MANUTENCAO, 15));

        locadora.cadastrarAluguel(new AluguelBasico("Ana Souza", 3, StatusAluguel.ATIVO));
        locadora.cadastrarAluguel(new AluguelExecutivo("Bruno Lima", 5, StatusAluguel.ATIVO,
                "Carlos Pereira"));
    }

}
