package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.Population;
import algorithms.DIPLOMA.util.Printers.DayPrinter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PopulationTest {

    private final static int MAX_ITER = 1000;
    private final int TEST_COUNT = 50;
    private Population pop;


    @Before
    public void setUp() throws Exception{
        pop = new Population();
        pop.initPool(1);
    }

    @Test
    public void init() {
        List<Genome> population = pop.init();

        assertTrue(population.size() > 0);
    }

    @Test
    public void selectElite() {
        int eliteSize = 1;
        ArrayList<Genome> population = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        Genome genome;
        Genome genome2;

        teachers.add(new Teacher("Russian1", 1, 1));
        teachers.add(new Teacher("Math1", 1, 1));
        teachers.add(new Teacher("Chemistry1", 1, 1));

        genome = new Genome(teachers);
        population.add(genome);

        teachers = new ArrayList<>();
        teachers.add(new Teacher("Russian2", 1, 1));
        teachers.add(new Teacher("Math2", 1, 1));
        teachers.add(new Teacher("Chemistry2", 1, 1));

        genome2 = new Genome(teachers);
        population.add(genome2);

        List<Genome> elitePathOfThePopulation = pop.selectElite(population, eliteSize);
        genome2 = elitePathOfThePopulation.get(0);

        assertEquals(eliteSize, elitePathOfThePopulation.size());
        assertEquals("Russian1", genome2.getDay().get(0).getName());
        assertEquals("Math1", genome2.getDay().get(1).getName());
        assertEquals("Chemistry1", genome2.getDay().get(2).getName());

    }

    @Test
    public void mutate() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Ruslan", 1, 1));
        teachers.add(new Teacher("Ruslan", 1, 1));
        teachers.add(new Teacher("Ruslan", 1, 1));
        teachers.add(new Teacher("Ruslan", 1, 1));
        teachers.add(new Teacher("Ruslan", 1, 1));

        Genome initGenome = new Genome(teachers);
        Genome mutatedGenome = pop.mutate(initGenome);

        String mutatedName = "";
        for (Teacher t : mutatedGenome.getDay()){
            if (!t.getName().equals("Ruslan")){
                mutatedName = t.getName();
                break;
            }
        }

        assertNotEquals(mutatedName, "Ruslan");
    }

    @Test
    public void mate() {
        ArrayList<Genome> population = new ArrayList<>();



    }

    @Ignore
    @Test
    public void test(){
        DayPrinter dayPrinter = new DayPrinter();
        for (int j = 0; j < TEST_COUNT; j++) {
            for (int i = 1; i <= 5; i++) {
                dayPrinter.printDayOfTheWeek(i);
                go();
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    private void go() {
        Population algorithm = new Population();
        algorithm.initPool(1);
        List<Genome> population = algorithm.init();

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