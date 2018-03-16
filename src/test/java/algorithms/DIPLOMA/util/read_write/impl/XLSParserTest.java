package algorithms.DIPLOMA.util.read_write.impl;

import algorithms.DIPLOMA.util.TeachersCreator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class XLSParserTest {
    private File inputFile;
    private XLSParser xlsParser;

    @Before
    public void setUp(){
        ClassLoader classLoader = getClass().getClassLoader();
        inputFile = new File(classLoader.getResource("read_write/xlsx/inputFile.xlsx").getFile());
        xlsParser = new XLSParser(inputFile);
    }

    @Test
    public void parse(){
        ArrayList<String> parseResult;
        parseResult = xlsParser.parse();

        int expectedAmountOfStrings = 6;
        assertEquals(expectedAmountOfStrings, parseResult.size());
    }
}