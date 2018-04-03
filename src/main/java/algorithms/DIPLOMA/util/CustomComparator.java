package algorithms.DIPLOMA.util;

import java.util.Comparator;

public class CustomComparator {
    public static Comparator<String> getComparator(){
        return new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            int extractInt(String person) {
                String num = person.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        };
    }
}
