package algorithms.DIPLOMA.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesExtractor {
    private static final String PROPERTIES_FILE = "app.config";
    private static String INPUT_FILEPATH;
    private static String OUTPUT_FILEPATH;

    static {
        extractProperties();
    }

    private static void extractProperties(){
        Properties properties = new Properties();

        try(InputStream inputStream = new FileInputStream(PROPERTIES_FILE)){

            properties.load(inputStream);
            INPUT_FILEPATH = properties.getProperty("inputFile");
            OUTPUT_FILEPATH = properties.getProperty("outputFile");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getInputFilepath() {
        return INPUT_FILEPATH;
    }

    public static String getOutputFilepath() {
        return OUTPUT_FILEPATH;
    }
}
