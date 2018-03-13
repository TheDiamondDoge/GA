package algorithms.DIPLOMA.util.Printers;

import algorithms.DIPLOMA.model.Genome;

import java.util.List;

public class TimetablePrinter {

    public void print(List<Genome> printable){

        for(int i = 0; i < printable.size(); i++) {
            System.out.print(i + " > ");
            System.out.println(printable.get(i));
        }
    }
}
