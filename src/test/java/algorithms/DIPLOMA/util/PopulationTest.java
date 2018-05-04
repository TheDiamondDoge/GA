package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.model.Day;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.creators.TeachersCreator;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PopulationTest {
    private static Population population;
    private static List<Day> initialPopulation;

    @Test
    public void createInitialPopulation() {

        initialPopulation = population.createInitialPopulation(1);

        int expectedSizeOfPopulation = 100;
        assertEquals(expectedSizeOfPopulation, initialPopulation.size());
    }

    @Test
    public void mutateGenome() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Russian", "subject",1, 1, "1a"));

        Day day = new Day(teachers);
        Population mock = mock(Population.class);
        when(mock.getRandomTeacher()).thenReturn(
                new Teacher("Randomised", "Randomised",2,2,"1б" ));

        Day mutatedDay = mock.mutateGenome(day);

        assertEquals(mutatedDay.getDay().get(0), "Randomised");
    }

    @Test
    public void selectElite() {
        Day day = mock(Day.class);
        when(day.calcFitness()).thenReturn(0);

        List<Day> population = new ArrayList<>();
        population.add(day);
        population.add(day);
        population.add(day);

        List<Day> elitePopulation = new Population().selectElite(population, 2);

        int expectedSizeOfElitePopulation = 2;
        assertTrue(elitePopulation.size() == expectedSizeOfElitePopulation);
    }

    @Test
    public void deleteGenesFromPool() {
        List<Teacher> teachers = TeachersCreator.getTeachersForDay(1);

        int initSizeOfGenesPool = population.getGenesPool().size();

        ArrayList<Teacher> firstTeacher = new ArrayList<>();
        firstTeacher.add(teachers.get(0));

        population.deleteGenesFromPool(new Day(firstTeacher));
        int sizeOfGenesPoolAfterDelete = population.getGenesPool().size();

        assertEquals(sizeOfGenesPoolAfterDelete, initSizeOfGenesPool - 1);
    }

}