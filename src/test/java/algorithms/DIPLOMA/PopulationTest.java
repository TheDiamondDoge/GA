package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.Population;
import algorithms.DIPLOMA.util.TeachersCreator;
import algorithms.DIPLOMA.util.printers.DayPrinter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PopulationTest {
    private Population pop;

    @Before
    public void setUp(){
        createPool();
        pop = new Population();
        pop.initPool(1);

    }

    private void createPool(){
        ArrayList<String> xlsStrings = new ArrayList<>();
        xlsStrings.add("Russian1;1;1");
        xlsStrings.add("Russian2;2-3;2");
        xlsStrings.add("Russian3;4,6;3");
        xlsStrings.add("Russian4;7;4");

        new TeachersCreator(xlsStrings).createTeachers();
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
        Genome mutatedGenome = pop.mutateGenome(initGenome);

        String mutatedName = "";
        for (Teacher t : mutatedGenome.getDay()){
            if (!t.getName().equals("Ruslan")){
                mutatedName = t.getName();
                break;
            }
        }
        assertNotEquals(mutatedName, "Ruslan");
    }
}