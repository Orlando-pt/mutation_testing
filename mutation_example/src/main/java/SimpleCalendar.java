public class SimpleCalendar {
    // TODO explicar o que isto e
    private final static int YEAR_OF_CHANGE = 1582;
    private final static int MONTH_OF_CHANGE = 8;
    private final static int DAY_OF_CHANGE = 4;

    public WeekDays getDayOfWeek(SimpleDate date) {
        SimpleDate gregorianDate = getGregorianDate(date);

        int century = parseCentury(gregorianDate.getYear());
        int yearInTheCentury = parseYearInTheCentury(gregorianDate.getYear());

        double result = gregorianDate.getDay() + 5 * century + yearInTheCentury + (century / 4) + (yearInTheCentury / 4)
                + (2.6 * parseMonth(gregorianDate.getMonth()) - 0.2);

        int res = (int) (result % 7);
        return WeekDays.valueOf(res);
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
        if (date.getYear() < YEAR_OF_CHANGE)
            return false;

        if (date.getYear() == YEAR_OF_CHANGE && date.getMonth() < MONTH_OF_CHANGE)
            return false;

        if (date.getYear() == YEAR_OF_CHANGE && date.getMonth() == MONTH_OF_CHANGE && date.getDay() <= DAY_OF_CHANGE)
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
        int century = parseCentury(date.getYear()) * 100;

        int subtract = 0;

        if (date.getYear() > century) {
            century += 100;
        }

        for (; century < 1582; century += 100) {
            if (century % 400 != 0)
                subtract += 1;
        }

        date.addDays(10 - subtract);

        return date;
    }

}
