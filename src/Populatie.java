import java.util.ArrayList;

/**
 * Created by MariusDK on 07.05.2017.
 */
public class Populatie {
    public ArrayList<Cromozom> pop;
    public int dimensiune;

    public Populatie() {
    }

    public Populatie(ArrayList<Cromozom> pop)
    {
        this.pop = new ArrayList<>(pop);
        this.dimensiune=pop.size();
    }

    public ArrayList<Cromozom> getPop() {
        return pop;
    }

    public void setPop(ArrayList<Cromozom> pop) {
        this.pop=pop;
    }

    public int getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(int dimensiune) {
        this.dimensiune = dimensiune;
    }
    public void add(Cromozom c)
    {
        pop.add(c);
    }

}
