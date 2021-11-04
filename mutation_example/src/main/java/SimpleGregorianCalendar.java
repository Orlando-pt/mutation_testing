import java.util.HashMap;
import java.util.Map;

public class SimpleGregorianCalendar {

    // TODO explicar o que isto e
    private final static int YEAROFCHANGE = 1582;
    private final static int MONTHOFCHANGE = 8;
    private final static int DAYOFCHANGE = 4;

    public SimpleGregorianCalendar() {}

    public WeekDays getDayOfWeek(SimpleDate date) {
        var gregorianDate = getGregorianDate(date);


        return null;
    }

    // method that checks
    public SimpleDate getGregorianDate(SimpleDate date) {
        // verificar se é antes ou depois de 1582

        // se for depois quer dizer q ja é gregoriana
        if (dateIsGregorian(date)) return date;

        // sn fazia simplesmente a transformacao
        return parseJulianToGregorian(date);
    }

    public boolean dateIsGregorian(SimpleDate date) {
        if (date.getYear() < 1582)
            return false;

        if (date.getYear() == 1582 && date.getMonth() < 10)
            return false;

        if (date.getYear() == 1582 && date.getMonth() == 10 && date.getDay() < 5)
            return false;

        return true;
    }

    private int parseMonth(int month) {
        if (month > 2) return month - 2;

        return month + 10;
    }

    private int parseCentury(int year) {
        return Math.floorDiv(year, 100);
    }

    private int parseYearInTheCentury(int year) {
        return year % 100;
    }

    private SimpleDate parseJulianToGregorian(SimpleDate date) {
        return null;
    }

}
