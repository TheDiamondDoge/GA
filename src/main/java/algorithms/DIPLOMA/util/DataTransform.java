package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.model.Day;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.creators.TeachersCreator;

import java.util.*;

public class DataTransform {
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

    public static Map<Integer, Map<String, String[]>> getTeachersSchedule(Map<Integer, ArrayList<Day>> data){
        Map<Integer, Map<String, String[]>> teachersSchedule = new HashMap<>();
        Map<String, String[]> tempSchedule = new HashMap<>();
        String[] grades;

        for (int i : data.keySet()){
            initMap(tempSchedule);
            for (Day day : data.get(i)){
                for (Teacher teacher : day.getDay()){
                    String teachersName = teacher.getName();
                    grades = tempSchedule.get(teachersName);
                    grades[teacher.getLesson()] = teacher.getGrade() + teacher.getSubjectName();
                    tempSchedule.put(teachersName, grades);
                }
            }
            teachersSchedule.put(i, tempSchedule);
            tempSchedule = new HashMap<>();
        }
        return teachersSchedule;
    }

    private static void initMap(Map<String, String[]> map){
        List<String> teachers = TeachersCreator.getTeacherNames();
        for (String name : teachers){
            map.put(name, new String[10]);
        }
    }
}
