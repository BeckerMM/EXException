package classes;
import exceptions.*;
import java.util.ArrayList;

public class Jogo {
    private final ArrayList<Object> barco = new ArrayList<>();
    private final ArrayList<Object> ladoEsquerdoRio = new ArrayList<>();
    private final ArrayList<Object> ladoDireitoRio = new ArrayList<>();
    private int round = 0;

    public Jogo() {
        this.barco.add(new Campones());

        this.ladoDireitoRio.add(new Lobo());
        this.ladoDireitoRio.add(new Ovelha());
        this.ladoDireitoRio.add(new Colve());
    }
    public ArrayList<Object> getBarco() {
        return barco;
    }

    public void setBarco(Object objeto) {
        if (barco.size() < 2) {
            this.barco.add(objeto);
            System.out.println("foi");
        } else {
            System.out.println("EXCEPTION");
            throw new BarcoLotadoExecption();
        }
    }

    public ArrayList<Object> getLadoEsquerdoRio() {
        return ladoEsquerdoRio;
    }

    public void setLadoEsquerdoRio(Object objeto) {
        if (this.ladoEsquerdoRio.size() < 3) {
            this.ladoEsquerdoRio.add(objeto);
        }
    }

    public ArrayList<Object> getLadoDireitoRio() {
        return ladoDireitoRio;
    }

    public void setLadoDireitoRio(Object objeto) {
        if (this.ladoDireitoRio.size() < 3) {
            this.ladoDireitoRio.add(objeto);
        }
    }

    public int getRound() {
        return round;
    }

    public void setRound() {
        this.round++;
    }

    public boolean verificarVencedor(){
        return ladoEsquerdoRio.size() == 3 && ladoDireitoRio.size() == 0;
    }

    public Ovelha getOvelha(){
        for (Object objeto : ladoDireitoRio) {
            if (objeto instanceof Ovelha){
                return (Ovelha) objeto;
            }
        }
        for (Object objeto: ladoEsquerdoRio) {
            if (objeto instanceof  Ovelha){
                return (Ovelha) objeto;
            }
        }
        return null;
    }
    public Lobo getLobo(){
        for (Object objeto : ladoDireitoRio) {
            if (objeto instanceof Lobo){
                return (Lobo) objeto;
            }
        }
        for (Object objeto: ladoEsquerdoRio) {
            if (objeto instanceof  Lobo){
                return (Lobo) objeto;
            }
        }
        return null;
    }
    public Colve getColve(){
        for (Object objeto : ladoDireitoRio) {
            if (objeto instanceof Colve){
                return (Colve) objeto;
            }
        }
        for (Object objeto: ladoEsquerdoRio) {
            if (objeto instanceof  Colve){
                return (Colve) objeto;
            }
        }
        return null;
    }

    @Override
    public String toString() {

        if (round % 2 == 0) {
            return "====== Lado Direito ======= " +
                    "\n" + (this.ladoEsquerdoRio.size() == 0 ? "[ , , ]" : this.ladoEsquerdoRio) + " -------- "
                    + barco
                    + " -- " + (this.ladoDireitoRio.size() == 0 ? "[ , , ]" : this.ladoDireitoRio);
        } else {
            return "====== Lado Esquerdo ======= " +
                    "\n" + (this.ladoEsquerdoRio.size() == 0 ? "[ , , ]" : this.ladoEsquerdoRio) + " -- "
                    + barco
                    + " -------- " + (this.ladoDireitoRio.size() == 0 ? "[ , , ]" : this.ladoDireitoRio);
        }

    }
}
