import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by MariusDK on 07.05.2017.
 */
public class Algoritm {
    public ArrayList<String> cuvinte;

    public void initializare(Populatie populatie)
    {

       ArrayList<Integer> listaPtGenerare=new ArrayList<>();
       ArrayList<Cromozom> pop=new ArrayList<>();
       for (String o:cuvinte)
       {
           listaPtGenerare.add(cuvinte.indexOf(o));
       }
       int k=0;
       //Cati indivizi se afla in prima generatie
        //luam 10 indivizi
       while (k<=9)
       {
           Collections.shuffle(listaPtGenerare);
           Cromozom c=new Cromozom(listaPtGenerare);
// populatie.add(c);
           pop.add(c);
           k++;
       }
       populatie.setDimensiune(k);
       populatie.setPop(pop);
    }
    public ArrayList<Integer> despartireForma(Cromozom o,String status)
    {

        ArrayList<Integer> forma = new ArrayList<>();
        if (status.equals("lateral")) {
            for (int i = 1; i <= (o.getForma().size()) / 2; i++)
            {
                forma.add(o.getForma().get(i));
            }
        }
        else {
            for (int i=((o.getForma().size()) / 2);i<o.getForma().size();i++)
            {
                forma.add(o.getForma().get(i));
            }
        }
        return forma;

    }
    public void evaloarePop(Populatie populatie)
    {
        for (Cromozom o:populatie.getPop())
        {
            o.setFitness(fitness(transforMatriceLinie(o),transforMatriceColoana(o)));
        }
    }
    public void evaloareIndivid(Cromozom c)
    {

            c.setFitness(fitness(transforMatriceLinie(c),transforMatriceColoana(c)));

    }
    public void print(Cromozom o)
    {
        ArrayList<Integer> list=despartireForma(o,"lateral");
        String[][] matriceLinie=new String[list.size()][list.size()];
        for (int i=0;i<list.size();i++)
        {
            String[] s=cuvinte.get(list.get(i)).split("");
            for (int c=0;c<s.length;c++)
            {
                matriceLinie[i][c]=s[c];
                System.out.print(matriceLinie[i][c]);
            }
            System.out.println();
        }

    }
    public String[][] transforMatriceLinie(Cromozom o)
    {
        ArrayList<Integer> list=despartireForma(o,"lateral");
        String[][] matriceLinie=new String[list.size()][list.size()];
        for (int i=0;i<list.size();i++)
        {
            String[] s=cuvinte.get(list.get(i)).split("");
            for (int c=0;c<s.length;c++)
            {
                matriceLinie[i][c]=s[c];
            }
        }
        return matriceLinie;
    }
    public String[][] transforMatriceColoana(Cromozom o)
    {
        ArrayList<Integer> list=despartireForma(o,"coloana");
        String[][] matriceColoana=new String[list.size()][list.size()];
        for (int i=0;i<list.size();i++)
        {
            String[] s=cuvinte.get(list.get(i)).split("");
            for (int l=0;l<s.length;l++)
            {
                matriceColoana[l][i]=s[l];
            }
        }
        return matriceColoana;
    }
    public int fitness(String[][] matriceLinie,String[][] matriceColoana)
    {
        int fit=0;
        for (int i=0;i<matriceColoana.length;i++)
        {
            for (int j=0;j<matriceColoana.length;j++)
            {
                if (!matriceColoana[i][j].equals(matriceLinie[i][j]))
                {
                    fit++;
                }
            }
        }
        return fit;


    }
    public Cromozom Selectie(Populatie populatie)
    {
        Random randomGenerator = new Random();
        int ind1 = randomGenerator.nextInt(populatie.getDimensiune());
        int ind2 = randomGenerator.nextInt(populatie.getDimensiune());
        while (ind1==ind2)
        {
            ind2 = randomGenerator.nextInt(populatie.getDimensiune());
        }
        if ((populatie.getPop().get(ind1).getFitness()>populatie.getPop().get(ind2).getFitness())) {
            return populatie.getPop().get(ind1);
        }else {
            return populatie.getPop().get(ind2);
        }
    }
    public ArrayList<Cromozom> Incrucisare(Cromozom p1,Cromozom p2)
    {
        ArrayList<Cromozom> copii=new ArrayList<>();

        int maxInt = (int)(Math.random() * ((p1.getForma().size())));
        int minInt = (int)(Math.random() * ((p2.getForma().size())));
        if (minInt>maxInt)
        {
            int aux=minInt;
            minInt=maxInt;
            maxInt=aux;
        }
        Cromozom fiu1=new Cromozom();
        ArrayList<Integer> fiu1L=new ArrayList<>();
        while (fiu1L.size()<p1.getForma().size())
        {
            fiu1L.add(-1);
        }
        for (int i=minInt;i<=maxInt;i++)
        {
            fiu1L.set(i,p1.getForma().get(i));
        }
        int i=maxInt+1;

        for (int k=0;k<p2.getForma().size();k++)
        {
            if (!Verificare(fiu1L, p2.getForma().get(k))) {
                if ((i > maxInt) && (i < p2.getForma().size())) {
                    fiu1L.set(i, p2.getForma().get(k));
                    i++;
                }
            }
            if (i==p2.getForma().size())
            {
                i=0;
            }
            if (!Verificare(fiu1L, p2.getForma().get(k)))
                {
                    if (i<minInt)
                    {
                        fiu1L.set(i,p2.getForma().get(k));
                        i++;
                    }
                }
        }
        fiu1.setForma(fiu1L);
        copii.add(fiu1);
        Cromozom fiu2=new Cromozom();
        ArrayList<Integer> fiu2L=new ArrayList<>();
        while (fiu2L.size()<p2.getForma().size())
        {
            fiu2L.add(-1);
        }
        for (int j=minInt;j<=maxInt;j++)
        {
            fiu2L.set(j,p2.getForma().get(j));
        }
        int j=maxInt+1;
        for (int k=0;k<p1.getForma().size();k++) {
            if (!Verificare(fiu2L, p1.getForma().get(k))) {
                if ((j > maxInt) && (j < p1.getForma().size())) {
                    fiu2L.set(j, p1.forma.get(k));
                    j++;
                }
            }
                if (j==p1.getForma().size())
                {
                    j=0;
                }
            if (!Verificare(fiu2L, p1.getForma().get(k)))
            {
                if (j<minInt)
                {
                    fiu2L.set(j,p1.forma.get(k));
                    j++;
                }
            }
        }
        fiu2.setForma(fiu2L);
        copii.add(fiu2);
        return copii;
    }
    public void Mutatie(Cromozom c)
    {
        int aux;

        int poz1 = (int)(Math.random() * ((c.getForma().size())));
        int poz2 = (int)(Math.random() * ((c.getForma().size())));
        while (poz1==poz2)
        {
            poz2 =(int)(Math.random() * ((c.getForma().size())));;
        }
        aux=c.getForma().get(poz1);
        c.getForma().set(poz1,c.getForma().get(poz2));
        c.getForma().set(poz2,aux);
    }

    public boolean Verificare(ArrayList list,Integer val)
    {
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i)==val)
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getCuvinte() {
        return cuvinte;
    }

    public void setCuvinte(ArrayList<String> cuvinte) {
        this.cuvinte = cuvinte;
    }

    public void AlgoritmEvolutiv()
    {
        int NG=0;

        Populatie populatie=new Populatie();
        //Populatie Newpopulatie=new Populatie();
        initializare(populatie);
        while (!Conditie(populatie))
        {
            ArrayList<Cromozom> Newpopulatie=new ArrayList<>();
            evaloarePop(populatie);
            while (Newpopulatie.size()<populatie.getDimensiune())
            {
                Cromozom M=Selectie(populatie);
                Cromozom F=Selectie(populatie);
                ArrayList<Cromozom> cromozomi=Incrucisare(M,F);
                Cromozom c1=cromozomi.get(0);
                Mutatie(c1);
                Cromozom c2=cromozomi.get(1);
                Mutatie(c2);
                evaloareIndivid(c1);
                evaloareIndivid(c2);
                Newpopulatie.add(c1);
                Newpopulatie.add(c2);
            }
            populatie=new Populatie(Newpopulatie);
            NG++;
            System.out.println("GENERATIA "+NG);
        }
        Cromozom c = SolutieFinala(populatie);
        print(c);

    }
//    public void evalPop(Populatie p)
//    {
//        for (Cromozom c:p.getPop())
//        {
//            c.setFitness(fitness(c));
//        }
//    }
//    public int fitness(Cromozom c)
//    {
//        int k=1;
//        int fit=0;
//        ArrayList<String> cuv=new ArrayList<>();
//        while (k<=c.getForma().size())
//        {
//            String s=cuvinte.get(c.getForma().get(k));
//            cuv.add(s);
//            fit++;
//            k++;
//        }
//        for (int i=0;i<cuv.get(1).length();i++)
//        {
//            String s=CuvinteColoana(cuv,i);
//            for (String cuvant:cuvinte)
//            {
//                if (s.equals(cuvant))
//                {
//                    fit++;
//                }
//            }
//            i++;
//        }
//        return fit;
//    }

    public String CuvinteColoana(ArrayList<String> cuv,int k)
    {
        String s="";
        for (String o:cuv)
        {
            char s_char = o.charAt(k);
            s=s+s_char;
        }
        return s;
    }
    public  Cromozom SolutieFinala(Populatie populatie)
    {
        Cromozom best=new Cromozom();
        for (Cromozom c:populatie.getPop())
        {
            if (best.getFitness()<c.getFitness())
            {
                best=c;
            }
        }
        return  best;
    }
    public boolean Conditie(Populatie populatie)
    {
        for (Cromozom c:populatie.getPop())
        {
            if (c.getFitness()==0)
            {
                return true;
            }
        }
        return false;
    }

}
