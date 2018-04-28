package algorithms.DIPLOMA.util.creators;

import algorithms.DIPLOMA.data.GradeDataObject;
import algorithms.DIPLOMA.model.Teacher;
import algorithms.DIPLOMA.util.DataTransform;

import java.util.*;
import java.util.stream.Collectors;

public class TeachersCreator {
    private static List<Teacher> TEACHERS;
    private List<String> parsedXLSStrings;
    private List<String> allGradesFromXls;


    public TeachersCreator() {
    }

    public TeachersCreator(List<String> parsedXLSStrings) {
        this.parsedXLSStrings = parsedXLSStrings;
    }

    public void createTeachers() {
        ArrayList<Integer> lessons;
        Set<String> gradesSetTemp = new HashSet<>();
        allGradesFromXls = new ArrayList<>();
        TEACHERS = new ArrayList<>();

        for (String str : parsedXLSStrings) {
            String[] fieldsOfObject = str.split(";");
            if (fieldsOfObject.length > 0) {
                lessons = getNumbersFromString(fieldsOfObject[2]);
                List<String> grades = getGradesFromString(fieldsOfObject[4]);

                for (int i : lessons) {
                    for (String g : grades) {
                        GradeDataObject.GRADE = g;
                        gradesSetTemp.add(g);
                        int dayOfTheWeek = Character.getNumericValue(fieldsOfObject[3].charAt(0));
                        TEACHERS.add(new Teacher(fieldsOfObject[0], fieldsOfObject[1] , i,
                                dayOfTheWeek, g));
                    }
                }
            }
        }
        allGradesFromXls.addAll(gradesSetTemp);
        allGradesFromXls.sort(DataTransform.getComparator());

        if (allGradesFromXls.contains(""))
            allGradesFromXls.remove("");

        addPlaceholderdsForCorrectRandomWorking();
    }

    //TODO This function must take lambda to provide ints from string directly OR int value of string
    public ArrayList<Integer> getNumbersFromString(String rangeOfLessons) {
        String[] lessons = rangeOfLessons.split(",");
        ArrayList<Integer> result = new ArrayList<>();

        for (String str : lessons) {
            if (str.contains("-")) {
                for (int i = Character.getNumericValue(str.charAt(0)); i <= Character.getNumericValue(str.charAt(2)); i++) {
                    result.add(i);
                }
            } else {
                result.add(Character.getNumericValue(str.charAt(0)));
            }
        }
        return result;
    }

    public ArrayList<String> getGradesFromString(String rangeOfLessons) {
        String[] grades = rangeOfLessons.split(",");
        ArrayList<String> result = new ArrayList<>();

        for (String str : grades) {
            int firstGrade;
            int lastGrade;
            String gradeLetter;

            if (str.contains("-")) {
                String[] split = str.split("-");
                if (split[0].length() == 2) {
                    firstGrade = Character.getNumericValue(split[0].charAt(0));
                    gradeLetter = split[0].substring(1);
                } else {
                    firstGrade = Integer.parseInt(split[0].substring(0, 2));
                    gradeLetter = split[0].substring(2);
                }

                if (split[1].length() == 2) {
                    lastGrade = Character.getNumericValue(split[1].charAt(0));
                } else {
                    lastGrade = Integer.parseInt(split[1].substring(0, 2));
                }

                for (int i = firstGrade; i <= lastGrade; i++) {
                    result.add(i + gradeLetter);
                }
            } else {
                result.add(str);
            }
        }
        return result;
    }

    private static void addPlaceholderdsForCorrectRandomWorking(){
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 1,"1f"));
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 2,"1f"));
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 3,"1f"));
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 4,"1f"));
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 5,"1f"));
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 6,"1f"));
        TEACHERS.add(new Teacher("placeholder", "placeholder", 1, 7,"1f"));
    }

    public static List<Teacher> getTeachersForDay(int dayOfTheWeek) {
        return TEACHERS.stream().filter(teacher -> teacher.getDayOfTheWeek() == dayOfTheWeek).collect(Collectors.toList());
    }

    public static List<Teacher> getTEACHERS() {
        return TEACHERS;
    }

    public List<String> getAllGradesFromXls() {
        return allGradesFromXls;
    }
}
