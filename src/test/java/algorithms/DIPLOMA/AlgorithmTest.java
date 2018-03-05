package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.Printer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmTest {

    private final static int MAX_ITER = 1000;

    @Test
    public void test(){
        Printer printer = new Printer();
        for (int j = 0; j < 100; j++) {
            for (int i = 1; i <= 5; i++) {
                printer.printDayOfTheWeek(i);
                go(i);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public void go(int dayOfTheWeek) {
        Algorithm algorithm = new Algorithm();
        List<Genome> population = new ArrayList<>();

        algorithm.init(population, dayOfTheWeek);

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {
                ArrayList<Teacher> teachers = population.get(0).getDay();

                for(int j = 0; j < teachers.size(); j++){
                    System.out.print(i + " > ");
                    System.out.print(teachers.get(j).getName() + "-" + teachers.get(j).getLesson() + "; ");
                    assertEquals(j+1, teachers.get(j).getLesson());
                }

                System.out.println();
                break;
            }

            population = algorithm.mate(population);

        }

    }
}