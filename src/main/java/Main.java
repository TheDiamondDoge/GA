import algorithms.DIPLOMA.Algorithm;
import algorithms.DIPLOMA.util.Printer;

public class Main {
    public static void main(String[] args) {

        Algorithm algorithm = new Algorithm();
        Printer printer = new Printer();

        for (int i = 0; i < 3; i++){
            algorithm.go(1);
        }
    }
}
