package algorithms.DIPLOMA.util.creators;

import java.util.*;

public class LimitsCreatorWeekly {
    private List<String> parsedLimits;

    public LimitsCreatorWeekly(List<String> parsedLimits) {
        this.parsedLimits = parsedLimits;
    }

    public Map<String, Map<String, Integer>> createLimitsFromList(){
        Map<String, Map<String, Integer>>limits = new HashMap<>();
        Map<String, Integer> template = new HashMap<>();

        String[] subjects = parsedLimits.get(0).split(";");
        for (int i = 1; i < subjects.length; i++){
            template.put(subjects[i], 0);
        }

        for (int i = 1; i < parsedLimits.size(); i++){
            makeValuesZero(template);
            String[] subjectsLimits = parsedLimits.get(i).split(";");
            String grade = subjectsLimits[0];
            subjectsLimits = Arrays.copyOfRange(subjectsLimits, 1, subjectsLimits.length);

            for (int j = 0; j < subjectsLimits.length; j++){
                template.put(subjects[j], Integer.parseInt(subjectsLimits[j]));
            }

            limits.put(grade, new HashMap<>(template));
        }

        return limits;
    }

    private void makeValuesZero(Map<String, Integer> map){
        Set<String> keys = map.keySet();
        for (String key : keys){
            map.put(key, 0);
        }
    }
}
