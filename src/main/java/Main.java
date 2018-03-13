import algorithms.DIPLOMA.Algorithm;
import algorithms.DIPLOMA.util.Printer;
import algorithms.DIPLOMA.util.read_write.XLSParser;

public class Main {

    public static void main(String[] args) {

        Algorithm algorithm = new Algorithm();
        Printer printer = new Printer();
        new XLSParser().parse();

        for (int x = 1; x < 6; x++) {
            printer.printDayOfTheWeek(x);
            algorithm.init(x);

            for (int i = 0; i < 3; i++) {
                algorithm.go();
            }
        }
    }
}
