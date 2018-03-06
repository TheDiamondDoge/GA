package algorithms.DIPLOMA;

import algorithms.DIPLOMA.model.Teacher;

import java.util.ArrayList;

public class Genes {
    private static ArrayList<Teacher> initialPool = new ArrayList<>();


    public static ArrayList<Teacher> generate(){

//        initialPool.add(new Teacher("Russian", 1));
//        initialPool.add(new Teacher("Russian", 2));
//        initialPool.add(new Teacher("Russian", 3));
//        initialPool.add(new Teacher("Russian", 4));
//        initialPool.add(new Teacher("Russian", 5));
//        initialPool.add(new Teacher("Math", 2));
//        initialPool.add(new Teacher("Math", 4));
//        initialPool.add(new Teacher("Math", 5));
//        initialPool.add(new Teacher("English", 3));
//        initialPool.add(new Teacher("English", 1));
//        initialPool.add(new Teacher("Litra", 1));
//        initialPool.add(new Teacher("Litra", 2));
//        initialPool.add(new Teacher("Litra", 3));
//        initialPool.add(new Teacher("Phys", 2));
//        initialPool.add(new Teacher("Phys", 5));

        //mon
        initialPool.add(new Teacher("Russian1", 1, 1));
        initialPool.add(new Teacher("Math1", 1, 1));
        initialPool.add(new Teacher("Phys1", 1, 1));
        initialPool.add(new Teacher("Russian1", 2, 1));
        initialPool.add(new Teacher("Math1", 2, 1));
        initialPool.add(new Teacher("Litr1", 2, 1));
        initialPool.add(new Teacher("English1", 3, 1));
        initialPool.add(new Teacher("Litr1", 3, 1));
        initialPool.add(new Teacher("Phys1", 3, 1));
        initialPool.add(new Teacher("Math1", 4, 1));
        initialPool.add(new Teacher("English1", 4, 1));
        initialPool.add(new Teacher("Litra1", 4, 1));
        initialPool.add(new Teacher("Litra1", 5, 1));
        initialPool.add(new Teacher("Russian1", 5, 1));
        initialPool.add(new Teacher("Math1", 5, 1));

        //Because we will never reach last value with randomTeacher()
        initialPool.add(new Teacher("placeholder", -1, -1));

////        tue
//        initialPool.add(new Teacher("Math2", 1, 2));
//        initialPool.add(new Teacher("Math2", 2, 2));
//        initialPool.add(new Teacher("Math2", 3, 2));
//        initialPool.add(new Teacher("Math2", 4, 2));
//        initialPool.add(new Teacher("Math2", 5, 2));
//        initialPool.add(new Teacher("Phys2", 2, 2));
//        initialPool.add(new Teacher("Phys2", 3, 2));
//        initialPool.add(new Teacher("Phys2", 4, 2));
//        initialPool.add(new Teacher("Phys2", 5, 2));
//        initialPool.add(new Teacher("English2", 1, 2));
//        initialPool.add(new Teacher("English2", 2, 2));
//        initialPool.add(new Teacher("English2", 4, 2));
//
//        //wed
//        initialPool.add(new Teacher("Russian3", 1, 3));
//        initialPool.add(new Teacher("Russian3", 2, 3));
//        initialPool.add(new Teacher("Russian3", 3, 3));
//        initialPool.add(new Teacher("Litra3", 4, 3));
//        initialPool.add(new Teacher("Litra3", 5, 3));
//        initialPool.add(new Teacher("Phys3", 1, 3));
//        initialPool.add(new Teacher("Phys3", 2, 3));
//        initialPool.add(new Teacher("Phys3", 3, 3));
//        initialPool.add(new Teacher("Phys3", 4, 3));
//        initialPool.add(new Teacher("Phys3", 5, 3));
//        initialPool.add(new Teacher("English3", 2, 3));
//        initialPool.add(new Teacher("English3", 3, 3));
//        initialPool.add(new Teacher("English3", 5, 3));
//
//        //thurs
//        initialPool.add(new Teacher("Math4", 1, 4));
//        initialPool.add(new Teacher("Math4", 2, 4));
//        initialPool.add(new Teacher("Math4", 3, 4));
//        initialPool.add(new Teacher("Math4", 4, 4));
//        initialPool.add(new Teacher("Math4", 5, 4));
//        initialPool.add(new Teacher("Phys4", 2, 4));
//        initialPool.add(new Teacher("Phys4", 3, 4));
//        initialPool.add(new Teacher("Phys4", 4, 4));
//        initialPool.add(new Teacher("Phys4", 5, 4));
//        initialPool.add(new Teacher("English4", 1, 4));
//        initialPool.add(new Teacher("English4", 2, 4));
//        initialPool.add(new Teacher("English4", 4, 4));
//
//
//        //fri
//        initialPool.add(new Teacher("Math5", 4, 5));
//        initialPool.add(new Teacher("Math5", 5, 5));
//        initialPool.add(new Teacher("Phys5", 2, 5));
//        initialPool.add(new Teacher("Phys5", 3, 5));
//        initialPool.add(new Teacher("Phys5", 4, 5));
//        initialPool.add(new Teacher("Phys5", 5, 5));
//        initialPool.add(new Teacher("Russian5", 1, 5));
//        initialPool.add(new Teacher("Russian5", 2, 5));
//        initialPool.add(new Teacher("Russian5", 4, 5));


        return initialPool;
    }
}
