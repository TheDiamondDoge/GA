package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.model.Genome;
import algorithms.DIPLOMA.model.Teacher;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class XLSWriterTest {
    private XLSWriter xlsWriter;
    private File outputFile;
    private Map<String, ArrayList<Genome>> inputData;

    @Before
    public void setUp(){
        ClassLoader classLoader = getClass().getClassLoader();
        outputFile = new File(classLoader.getResource("read_write/xlsx/outputFile.xlsx").getFile());
        xlsWriter = new XLSWriter(outputFile);
        setUpInputData();
    }

    @Test
    public void write() {
        xlsWriter.write(inputData);
    }

    private void setUpInputData(){
        ArrayList<Genome> genomes = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        inputData = new HashMap<>();
        String key = "Monday";
        Genome genome;

        teachers.add(new Teacher("Russian1", 1, 1));
        teachers.add(new Teacher("Math1", 2, 1));
        teachers.add(new Teacher("Phys1", 3, 1));

        genome = new Genome(teachers);
        genomes.add(genome);

        teachers = new ArrayList<>();
        teachers.add(new Teacher("Russian2", 1, 2));
        teachers.add(new Teacher("Math2", 2, 2));
        teachers.add(new Teacher("Phys2", 3, 2));

        genome = new Genome(teachers);
        genomes.add(genome);
        inputData.put(key, genomes);
    }
}