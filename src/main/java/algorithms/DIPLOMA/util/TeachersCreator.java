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
        ArrayList<String> grades;
        teachers = new ArrayList<>();

        for (String str : parsedXLSStrings){
            String[] fieldsOfObject = str.split(";");
            lessons = getNumbersFromString(fieldsOfObject[1]);
            grades = getGradesFromString(fieldsOfObject[3]);

            for (int i : lessons){
                for (String g : grades) {
                    teachers.add(new Teacher(fieldsOfObject[0], i,
                            Character.getNumericValue(fieldsOfObject[2].charAt(0)), g));
                }
            }
        }
    }

    //TODO This function must take lambda to provide ints from string directly OR int value of string
    public ArrayList<Integer> getNumbersFromString(String rangeOfLessons){
        String[] lessons = rangeOfLessons.split(",");
        ArrayList<Integer> result = new ArrayList<>();

        for (String str : lessons){
            if (str.contains("-")){
                for (int i = Character.getNumericValue(str.charAt(0)); i <= Character.getNumericValue(str.charAt(2)); i++){
                    result.add(i);
                }
            } else {
                result.add(Character.getNumericValue(str.charAt(0)));
            }
        }
        return result;
    }

    public ArrayList<String> getGradesFromString(String rangeOfLessons){
        String[] grades = rangeOfLessons.split(",");
        ArrayList<String> result = new ArrayList<>();

        for (String str : grades){
            int firstGrade;
            int lastGrade;
            String gradeLetter;

            if (str.contains("-")){
                String[] split = str.split("-");
                if(split[0].length() == 2){
                    firstGrade = Character.getNumericValue(split[0].charAt(0));
                    gradeLetter = split[0].substring(1);
                } else {
                    firstGrade = Integer.parseInt(split[0].substring(0, 2));
                    gradeLetter = split[0].substring(2);
                }

                if (split[1].length() == 2){
                    lastGrade = Character.getNumericValue(split[1].charAt(0));
                } else {
                    lastGrade = Integer.parseInt(split[1].substring(0, 2));
                }

                for (int i = firstGrade; i <= lastGrade; i++){
                    result.add(i + gradeLetter);
                }
            } else {
                result.add(str);
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
