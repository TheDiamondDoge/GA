package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Teacher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmTest {

    private final static int MAX_ITER = 1000;

    @Test
    public void test(){
        for(int i = 0; i < 100; i++){
            go();
        }
    }

    public void go() {
        Algorithm algorithm = new Algorithm();

        List<Genome> population = new ArrayList<Genome>();
        algorithm.init(population);

        for (int i = 0; i < MAX_ITER; i++) {
            Collections.sort(population);

            if (population.get(0).getFitness() == 0) {
                ArrayList<Teacher> teachers = population.get(0).getDay();

                System.out.print(i + " > ");

                for (Teacher t : population.get(0).getDay()){
                    System.out.print(t.getName() + "-" + t.getLesson() + "; ");
                }

                System.out.println(population.get(0).getFitness());

                for(int j = 0; j < teachers.size(); j++){
                    assertEquals(j+1, teachers.get(j).getLesson());
                }
                break;
            }

            population = algorithm.mate(population);

        }

    }
}