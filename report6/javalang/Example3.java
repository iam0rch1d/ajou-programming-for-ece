package javalang;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Example3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("Current date: " + currentDate);
        System.out.println("Current time: " + currentTime);
        System.out.println("Current date & time: " + currentDateTime);

        String exactTime = "Year " + currentDateTime.getYear() + ", ";
        exactTime += "Month " + currentDateTime.getMonthValue() + ", ";
        exactTime += "Day " + currentDateTime.getDayOfMonth() + ", ";
        exactTime += "Weekday " + currentDateTime.getDayOfWeek() + ", ";
        exactTime += "Hour " + currentDateTime.getHour() + ", ";
        exactTime += "Minute " + currentDateTime.getMinute() + ", ";
        exactTime += "Second " + currentDateTime.getSecond();

        System.out.println("Current exact time: " + exactTime);

        int plusDay = 100;
        int plusWeek = 10;

        System.out.println(plusDay + " day(s) after from today: " + currentDate.plusDays(plusDay));
        System.out.println(plusWeek + " week(s) after from today: " + currentDate.plusWeeks(plusWeek));
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print("Enter your birth date. ('[year] [month] [day]' format): ");

        LocalDate birthDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

        System.out.println("Your birthday: " + toString(birthDate));
        System.out.println("You was born " + birthDate.until(currentDate, ChronoUnit.DAYS) + " day(s) ago.");
        System.out.println("You are " + birthDate.until(currentDate, ChronoUnit.YEARS) + " year(s) old.");

        LocalDate centenaryDate = birthDate.plusYears(100);

        System.out.println("Your 100th birthday: " + toString(centenaryDate));
        System.out.println("Week(s) left until your 100th birthday: "
            + currentDate.until(centenaryDate, ChronoUnit.WEEKS)
        );
        System.out.println("Day(s) left until your 100th birthday: "
            + currentDate.until(centenaryDate, ChronoUnit.DAYS)
        );

        scanner.close();
    }

    static String toString(LocalDate localDate) {
        return "Year "
            + localDate.getYear()
            + ", "
            + "Month "
            + localDate.getMonth()
            + ", "
            + "Day "
            + localDate.getDayOfMonth();
    }
}
