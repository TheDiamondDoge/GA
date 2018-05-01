package algorithms.DIPLOMA.util.creators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LimitsCreatorDaily {
    private List<String> parsedLimits;


    public LimitsCreatorDaily(List<String> parsedLimits) {
        this.parsedLimits = parsedLimits;
    }

    public Map<String, Map<Integer,Integer>> createLimitsFromList(){
        Map<String, Map<Integer,Integer>>limits = new HashMap<>();

        for (String str : parsedLimits){
            String[] splitedStr = str.split(";");
            Map<Integer, Integer> temp = new HashMap<>();
            for (int i = 1; i < splitedStr.length; i++){
                temp.put(i, Integer.parseInt(splitedStr[i]));
            }
            limits.put(splitedStr[0], temp);
        }

        return limits;
    }
}
