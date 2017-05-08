import java.util.ArrayList;

/**
 * Created by MariusDK on 07.05.2017.
 */
public class Cromozom {

    public ArrayList<Integer> forma;
    public int fitness;

    public ArrayList<Integer> getForma() {
        return forma;
    }

    public Cromozom() {
        this.forma=null;
        this.fitness=-1;
    }
    public Cromozom(ArrayList<Integer> NEWforma) {
        this.forma=new ArrayList<>(NEWforma);
        this.fitness=-1;
    }

    public void setForma(ArrayList<Integer> form) {
        this.forma=form;
    }
    public void adauga(int celula)
    {
        forma.add(celula);
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
