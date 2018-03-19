package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeachersCreator {
    private static List<Teacher> teachers;
    private List<String> parsedXLSStrings;

    public TeachersCreator() {
    }

    public TeachersCreator(List<String> parsedXLSStrings) {
        this.parsedXLSStrings = parsedXLSStrings;
    }

    public void createTeachers(){
        ArrayList<Integer> lessons;
        ArrayList<Integer> grades;
        teachers = new ArrayList<>();

        for (String str : parsedXLSStrings){
            String[] fieldsOfObject = str.split(";");
            lessons = getNumbersFromString(fieldsOfObject[1]);
            grades = getNumbersFromString(fieldsOfObject[3]);

            for (int i : lessons){
                for (int j : grades) {
                    teachers.add(new Teacher(fieldsOfObject[0], i, Character.getNumericValue(fieldsOfObject[2].charAt(0)), j));
                }
            }
        }
    }

    public ArrayList<Integer> getNumbersFromString(String rangeOfLessons){
        String[] lessons = rangeOfLessons.split(",");
        ArrayList<Integer> result = new ArrayList<>();

        for (String str : lessons){
            if (str.contains("-")){
                for (int i = Character.getNumericValue(str.charAt(0));
                     i <= Character.getNumericValue(str.charAt(2)); i++)
                {
                    result.add(i);
                }
            } else {
                result.add(Character.getNumericValue(str.charAt(0)));
            }
        }
        return result;
    }

    public static List<Teacher> getTeachersForDay(int dayOfTheWeek){
        return teachers.stream().filter(teacher -> teacher.getDayOfTheWeek() == dayOfTheWeek).collect(Collectors.toList());
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }
}
