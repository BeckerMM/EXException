import classes.Colve;
import classes.*;
import exceptions.ColveOvelhaException;
import exceptions.OvelhaLoboException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Jogo jogo;


    public static void main(String[] args) {
        do {
            menu();
        } while (true);

    }

    private static void menu() {
        System.out.println("""
                ===== Menu =====
                1- Iniciar partida
                2- Regras do jogo
                3- Sair
                """);
        int decisao = sc.nextInt();
        switch (decisao) {
            case 1 -> partida();
            case 2 -> regras();
            case 3 -> System.exit(0);
        }
    }

    private static void partida() {
        jogo = new Jogo();
        boolean vencedor;
        do {
            vencedor = jogo.verificarVencedor();
            if (!vencedor) {
                System.out.println(vencedor);
                System.out.println(jogo);
                System.out.println("""
                        ===== O que deseja fazer =====
                        1- Adicionar ao barco
                        2- Remover do barco
                        3- Atravessar
                        """);
                int decisao = sc.nextInt();
                switch (decisao) {
                    case 1 -> adicionarBarco();
                    case 2 -> removerBarco();
                    case 3 -> vencedor = atravessar();
                    default -> System.out.println("Valor inválido!");
                }
            }else {
                System.out.println("Você venceu!");
                System.out.println(jogo);
            }
        } while (!vencedor);

    }

    private static void removerBarco() {
        if (jogo.getRound() % 2 == 0) {
            jogo.setLadoDireitoRio(jogo.getBarco().get(1));
        } else {
            jogo.setLadoEsquerdoRio(jogo.getBarco().get(1));
        }
        jogo.getBarco().remove(1);
    }

    private static boolean atravessar() {
        jogo.setRound();
        try {
            verificao();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static void verificao() {
        Ovelha ovelha = jogo.getOvelha();
        Lobo lobo = jogo.getLobo();
        Colve colve = jogo.getColve();
        ArrayList<Object> ladoDireito = jogo.getLadoDireitoRio();
        ArrayList<Object> ladoEsquerdo = jogo.getLadoEsquerdoRio();
        if (ladoDireito.contains(ovelha) && ladoDireito.contains(colve) && !ladoDireito.contains(lobo)
                || ladoEsquerdo.contains(ovelha) && ladoEsquerdo.contains(colve) && !ladoEsquerdo.contains(lobo)) {
            throw new ColveOvelhaException();
        } else if (ladoDireito.contains(ovelha) && !ladoDireito.contains(colve) && ladoDireito.contains(lobo)
                || ladoEsquerdo.contains(ovelha) && !ladoEsquerdo.contains(colve) && ladoEsquerdo.contains(lobo)) {
            throw new OvelhaLoboException();
        }
    }


    private static void adicionarBarco() {
        int decisao = 0;
        try {
            if (jogo.getRound() % 2 == 0) {
                for (Object objetoFor : jogo.getLadoDireitoRio()) {
                    System.out.println(jogo.getLadoDireitoRio().indexOf(objetoFor) + " " + objetoFor);
                }
                System.out.println("4 - nenhum");
                decisao = sc.nextInt();
                if (decisao <= jogo.getLadoDireitoRio().size() && decisao >= 0) {
                    jogo.setBarco(jogo.getLadoDireitoRio().get(decisao));
                    jogo.getLadoDireitoRio().remove(decisao);
                }
            } else {
                for (Object objetoFor : jogo.getLadoEsquerdoRio()) {
                    System.out.println(jogo.getLadoEsquerdoRio().indexOf(objetoFor) + " " + objetoFor);
                }
                System.out.println("4 - nenhum");
                decisao = sc.nextInt();
                if (decisao <= jogo.getLadoEsquerdoRio().size() && decisao >= 0) {
                    jogo.setBarco(jogo.getLadoEsquerdoRio().get(decisao));
                    jogo.getLadoEsquerdoRio().remove(decisao);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void regras() {
        System.out.println("""
                ====== Regras do jogo =====
                Um camponês atravessou o rio com seu barco e foi para a cidade, onde comprou um lobo, uma ovelha e uma couve. Na volta para casa, enfrenta um problema:
                – ele precisa transportar os itens comprados para sua casa, que fica do outro lado rio, e o seu barco comporta apenas um item, além dele próprio.
                O barquinho pode levar e trazer itens quantas vezes for necessário, mas um item de cada vez.
                Como o camponês poderá transportar o lobo, a ovelha e a couve para o outro lado do rio?
                                
                Lembre-se de que:
                                
                O lobo e a ovelha não poderão ficar sozinhos em uma das margens do rio, pois o lobo poderá devorar a ovelha.
                A couve e a ovelha não poderão ficar sozinhas em uma das margens do rio, pois a ovelha poderá devorar a couve.
                O camponês não sai do barco.             
                """);
    }
}