package algorithms.DIPLOMA.util.printers;

public class DayPrinter {

    public static String dayOfTheWeekFromNumber(int dayOfTheWeek){
        switch (dayOfTheWeek){
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            default: return "Invalid number of the day";
        }
    }

    public void printDayOfTheWeek(int dayOfTheWeek){
        System.out.println(dayOfTheWeekFromNumber(dayOfTheWeek));
    }
}
