package algorithms.DIPLOMA.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PropertiesExtractorTest {
    private File propertiesFile;

    @Before
    public void setUp(){
        ClassLoader classLoader = getClass().getClassLoader();
        propertiesFile = new File(classLoader.getResource("testapp.properties").getPath().replaceAll("%20", " "));
        PropertiesExtractor.setPropertiesFile(propertiesFile);
    }

    @Test
    public void getInputFilepath() {
        String expectedInputFilepath = "read_write/xlsx/inputFile.xlsx";
        assertEquals(expectedInputFilepath, PropertiesExtractor.getInputFilepath());
    }

    @Test
    public void getOutputFilepath() {
        String expectedOutputFilepath = "read_write/xlsx/outputFile.xlsx";
        assertEquals(expectedOutputFilepath, PropertiesExtractor.getOutputFilepath());
    }
}