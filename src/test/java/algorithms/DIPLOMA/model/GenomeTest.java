package algorithms.DIPLOMA.model;

import algorithms.DIPLOMA.data.GradeDataObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenomeTest {

    @Test
    public void testCalcFitness() {
        GradeDataObject.GRADE = "5a";
        ArrayList<Teacher> teachers = new ArrayList<>();

        teachers.add(new Teacher("Teacher1", 3, 1, "5a"));
        teachers.add(new Teacher("Teacher2", 2, 1, "5a"));
        teachers.add(new Teacher("Teacher3", 1, 1, "5a"));

        Genome genome = new Genome(teachers);

        int expectedFitness = 4;

        assertEquals(expectedFitness, genome.getFitness());

        teachers = new ArrayList<>();

        teachers.add(new Teacher("Teacher3", 1, 1, "5a"));
        teachers.add(new Teacher("Teacher2", 2, 1, "5a"));
        teachers.add(new Teacher("Teacher1", 3, 1, "5a"));

        genome = new Genome(teachers);

        expectedFitness = 0;

        assertEquals(expectedFitness, genome.getFitness());
    }

    @Test
    public void andAnotherOne(){
        ArrayList<Teacher> teachers = new ArrayList<>();

        teachers.add(new Teacher("Teacher1", 1, 1, "5a"));
        teachers.add(new Teacher("Teacher2", 2, 1, "5a"));

        System.out.println(new Genome(teachers));
    }


    @Ignore
    @Test
    public void crap(){
        System.out.println(Integer.valueOf("qwe"));
    }
}