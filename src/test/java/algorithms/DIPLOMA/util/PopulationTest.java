package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PopulationTest {
    private static Population population;
    private static List<Genome> initialPopulation;

    @BeforeClass
    public static void setUp(){
        createPool();
        population = new Population();
        population.initPool(1);
    }

    private static void createPool(){
        ArrayList<String> xlsStrings = new ArrayList<>();
        xlsStrings.add("Russian1;1;1;1");
        xlsStrings.add("Russian2;2-3;1;1");
        xlsStrings.add("Russian3;4,6;1;1");
        xlsStrings.add("Russian4;7;1;1");

        new TeachersCreator(xlsStrings).createTeachers();
    }

    @Test
    public void createInitialPopulation() {
        initialPopulation = population.createInitialPopulation();

        int expectedSizeOfPopulation = 100;
        assertEquals(expectedSizeOfPopulation, initialPopulation.size());
    }

    @Test
    public void mutateGenome() {
        ArrayList<Teacher> teachers = new ArrayList<>();

        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));
        teachers.add(new Teacher("Russian", 1, 1, "1a"));

        Genome genome = new Genome(teachers);
        Genome mutatedGenome = population.mutateGenome(genome);
        String mutatedTeachersName = "";

        for (Teacher teacher : mutatedGenome.getDay()){
            if (!teacher.getName().equals("Russian")){
                mutatedTeachersName = teacher.getName();
            }
        }

        assertNotEquals(mutatedTeachersName, "");
        assertNotEquals(mutatedTeachersName, "Russian");
    }

    @Test
    public void selectElite() {
        List<Genome> elitePopulation = population.selectElite(initialPopulation, 1);

        int expectedSizeOfElitePopulation = 1;
        assertTrue(initialPopulation.size() > 1);
        assertEquals(expectedSizeOfElitePopulation, elitePopulation.size());
    }

    @Test
    public void deleteGenesFromPool() {
        List<Teacher> teachers = TeachersCreator.getTeachersForDay(1);

        int initSizeOfGenesPool = population.getGenesPool().size();

        ArrayList<Teacher> firstTeacher = new ArrayList<>();
        firstTeacher.add(teachers.get(0));

        population.deleteGenesFromPool(new Genome(firstTeacher));
        int sizeOfGenesPoolAfterDelete = population.getGenesPool().size();

        assertEquals(sizeOfGenesPoolAfterDelete, initSizeOfGenesPool - 1);
    }
}