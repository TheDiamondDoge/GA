import algorithms.DIPLOMA.Algorithm;
import algorithms.DIPLOMA.util.Printer;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Algorithm algorithm = new Algorithm();
        Printer printer = new Printer();

        for (int i = 0; i < 3; i++){
            algorithm.go(1);
        }

     /*   for(int i = 1; i <= 5; i++) {
            printer.printDayOfTheWeek(i);
            if (i == 3)
                System.out.print("");
            algorithm.go(i);
        }*/


    }
}
