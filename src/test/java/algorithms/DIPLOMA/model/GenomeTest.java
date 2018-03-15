package algorithms.DIPLOMA.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenomeTest {

    @Test
    public void testCalcFitness() {
        ArrayList<Teacher> teachers = new ArrayList<>();

        teachers.add(new Teacher("Teacher1", 3, 1));
        teachers.add(new Teacher("Teacher2", 2, 1));
        teachers.add(new Teacher("Teacher3", 1, 1));

        Genome genome = new Genome(teachers);

        int expectedFitness = 4;

        assertEquals(expectedFitness, genome.getFitness());

        teachers = new ArrayList<>();

        teachers.add(new Teacher("Teacher3", 1, 1));
        teachers.add(new Teacher("Teacher2", 2, 1));
        teachers.add(new Teacher("Teacher1", 3, 1));

        genome = new Genome(teachers);

        expectedFitness = 0;

        assertEquals(expectedFitness, genome.getFitness());
    }
}