package algorithms.DIPLOMA.util;

import algorithms.DIPLOMA.data.LessonLimitsDaily;
import algorithms.DIPLOMA.model.*;
import algorithms.DIPLOMA.util.creators.TeachersCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static algorithms.DIPLOMA.data.GradeDataObject.*;

public class Population {
    private static final double ELITE_RATE = 0.1;
    private static final double SURVIVE_RATE = 0.5;
    private static final double MUTATION_RATE = 0.5;
    private int populationSize = 100;
    private List<Teacher> DAILY_GENES_POOL;
    private List<Teacher> GENES_POOL;
    private int[] availableTeachersPerLesson;
    private int lessonsForClassMax = 10;

    public Population() {
    }

    public void initPool(int dayOfTheWeek){
        GENES_POOL = TeachersCreator.getTeachersForDay(dayOfTheWeek);
    }

    private void setAmountAvailableTeachersPerLesson(){
        int dailyLessonLimit = LessonLimitsDaily.getLessonLimit(GRADE);
        availableTeachersPerLesson = new int[dailyLessonLimit + 1];
        for (Teacher teacher : DAILY_GENES_POOL){
            if (teacher.getLesson() <= dailyLessonLimit) {
                availableTeachersPerLesson[teacher.getLesson()]++;
            }
        }
    }

    public List<Day> createInitialPopulation(int x) {
        DAILY_GENES_POOL = GENES_POOL.stream()
                .parallel()
                .filter((teacher -> teacher.getGrade().equals(GRADE) && teacher.getDayOfTheWeek() == x))
                .collect(Collectors.toList());

        setAmountAvailableTeachersPerLesson();
        lessonsForClassMax = calcTargetSize();
        return Stream.generate(() -> new Day(getRandomListForGenome()))
                .parallel()
                .limit(populationSize)
                .collect(Collectors.toList());
    }

    private List<Teacher> getRandomListForGenome(){
        return Stream.generate(this::getRandomTeacher)
                .parallel()
                .limit(lessonsForClassMax)
                .collect(Collectors.toList());
    }

    private int calcTargetSize(){
        for (int lessonNum = 1; lessonNum < availableTeachersPerLesson.length; lessonNum++){
            if(isThereAnyAvailableTeacher(lessonNum)){
                return lessonNum - 1;
            }
        }
        return availableTeachersPerLesson.length - 1;
    }

    private boolean isThereAnyAvailableTeacher(int lessonNum){
        return availableTeachersPerLesson[lessonNum] == 0;
    }

    public Day mutateGenome(Day day) {
        int extractionPosition = (int) (Math.random() * lessonsForClassMax);
        Teacher delta = getRandomTeacher();

        ArrayList<Teacher> teachers = day.getDay();
        teachers.set(extractionPosition, delta);

        return new Day(teachers);
    }

    private Teacher getRandomTeacher(){
        return DAILY_GENES_POOL.get((int) (Math.random() * DAILY_GENES_POOL.size() - 1));
    }

    //mate
    public List<Day> mergeRandomGenomes(List<Day> population) {
        int eliteSize = (int) (populationSize * ELITE_RATE);
        List<Day> children = selectElite(population, eliteSize);

        for (int i = eliteSize; i < populationSize; i++) {
            int firstRandomGenome = (int) (Math.random() * populationSize * SURVIVE_RATE);
            int secondRandomGenome = (int) (Math.random() * populationSize * SURVIVE_RATE);
            int pointOfMerge = (int) (Math.random() * lessonsForClassMax);

            Day day = mergeNewGenome(population.get(firstRandomGenome),
                    population.get(secondRandomGenome), pointOfMerge);

            if (Math.random() < MUTATION_RATE) {
                day = mutateGenome(day);
            }
            children.add(day);
        }
        return children;
    }

    public List<Day> selectElite(List<Day> population, int eliteSize) {
        List<Day> children = new ArrayList<>();
        for (int i = 0; i < eliteSize; i++) {
            children.add(new Day(population.get(i)));
        }
        return children;
    }

    private Day mergeNewGenome(Day g1, Day g2, int pointOfMerge) {
        ArrayList<Teacher> teachers = g1.getDay();

        for (int i = pointOfMerge; i < teachers.size(); i++) {
            teachers.set(i, g2.getDay().get(i));
        }

        return new Day(teachers);
    }

    public void deleteGenesFromPool(Day day){
        day.getDay().forEach(x -> deleteTeacherFromGenesPool(x, x.getLesson()));
    }

    private void deleteTeacherFromGenesPool(Teacher teacher, int lesson){
        int beforeSize = GENES_POOL.size();
        GENES_POOL.removeIf(x -> x.getName().equals(teacher.getName())
                              && x.getLesson() == teacher.getLesson()
                              && x.getDayOfTheWeek() == teacher.getDayOfTheWeek());

        availableTeachersPerLesson[lesson] =  availableTeachersPerLesson[lesson] - (beforeSize - GENES_POOL.size());
    }

    public List<Teacher> getGenesPool() {
        return DAILY_GENES_POOL;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int[] getAvailableTeachersPerLesson() {
        return availableTeachersPerLesson;
    }
}
