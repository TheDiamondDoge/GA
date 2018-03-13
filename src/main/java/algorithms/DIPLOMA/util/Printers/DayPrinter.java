package algorithms.DIPLOMA.util.Printers;

public class DayPrinter {

    public String dayOfTheWeekFromNumber(int dayOfTheWeek){
        switch (dayOfTheWeek){
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            default: return "Invalid number of the day";
        }
    }

    public void printDayOfTheWeek(int dayOfTheWeek){
        System.out.println(dayOfTheWeekFromNumber(dayOfTheWeek));
    }
}