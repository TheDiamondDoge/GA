package algorithms.DIPLOMA.util.creators;

import algorithms.DIPLOMA.util.PropertiesExtractor;
import algorithms.DIPLOMA.util.read_write.impl.XLSParser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LimitsCreator {
    private List<String> parsedLimits;
    private Map<String, Integer> limits;


    public Map<String, Integer> createLimitsFromList(){
        limits = new HashMap<>();

        parseLimitsFromFile();
        for (String str : parsedLimits){
            String[] splitedStr = str.split(";");
            limits.put(splitedStr[0], Integer.parseInt(splitedStr[1]));
        }

        return limits;
    }

    private void parseLimitsFromFile(){
        File fileForParse = new File(PropertiesExtractor.getLimitsFilepath());
        XLSParser xlsParser = new XLSParser(fileForParse);
        parsedLimits = xlsParser.parse();
    }
}
