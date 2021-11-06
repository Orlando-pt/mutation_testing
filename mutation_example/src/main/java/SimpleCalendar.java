import java.time.LocalDateTime;

public class SimpleCalendar {
    // TODO explicar o que isto e
    private final static int YEAR_OF_CHANGE = 1582;
    private final static int MONTH_OF_CHANGE = 10;
    private final static int DAY_OF_CHANGE = 4;

    public WeekDays getDayOfWeek(LocalDateTime date) {
        var gregorianDate = getGregorianDate(date);

        int year = gregorianDate.getYear();

        if (gregorianDate.getMonthValue() < 3){
            year-=1;
        }

        int century = parseCentury(year);
        int yearInTheCentury = parseYearInTheCentury(year);

        long result = Math.round(gregorianDate.getDayOfMonth() + 5 * century + yearInTheCentury + Math.floor((century / 4)) + Math.floor((yearInTheCentury / 4))
                + Math.floor((2.6 * parseMonth(gregorianDate.getMonthValue()) - 0.2)));

        return WeekDays.valueOf(Math.floorMod(result, 7));
    }

    // method that checks
    public LocalDateTime getGregorianDate(LocalDateTime date) {
        // verificar se é antes ou depois de 1582

        // se for depois quer dizer q ja é gregoriana
        if (dateIsGregorian(date)) return date;

        // sn fazia simplesmente a transformacao
        return parseJulianToGregorian(date);
    }

    public boolean dateIsGregorian(LocalDateTime date) {
        if (date.getYear() < YEAR_OF_CHANGE)
            return false;

        if (date.getYear() == YEAR_OF_CHANGE && date.getMonthValue() < MONTH_OF_CHANGE)
            return false;

        if (date.getYear() == YEAR_OF_CHANGE && date.getMonthValue() == MONTH_OF_CHANGE && date.getDayOfMonth() <= DAY_OF_CHANGE)
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

    private LocalDateTime parseJulianToGregorian(LocalDateTime date) {
        int century = parseCentury(date.getYear()) * 100;

        int subtract = 0;

        if (date.getYear() > century) {
            century += 100;
        }

        for (; century < 1582; century += 100) {
            if (century % 400 != 0)
                subtract += 1;
        }

        return date.plusDays(10 - subtract);
    }

}
