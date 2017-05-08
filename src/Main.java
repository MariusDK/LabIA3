import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MariusDK on 08.05.2017.
 */
public class Main {
    public static void main(String[] arg)
    {
      System.out.println("Introduceti n:");
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      Algoritm a=new Algoritm();
      ArrayList<String> sL=new ArrayList<>();
      for (int i=1;i<=2*n;i++)
      {
          String s=sc.next();
          sL.add(s);
      }
      a.setCuvinte(sL);
      a.AlgoritmEvolutiv();
    }
}
