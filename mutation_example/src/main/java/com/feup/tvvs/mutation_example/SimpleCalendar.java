package com.feup.tvvs.mutation_example;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleCalendar {
    // TODO explicar o que isto e
    private final static int YEAR_OF_CHANGE = 1582;
    private final static int MONTH_OF_CHANGE = 10;
    private final static int DAY_OF_CHANGE = 4;

    public WeekDays getDayOfWeek(LocalDateTime date) {
        LocalDateTime gregorianDate = getGregorianDate(date);

        int year = gregorianDate.getYear();

        if (gregorianDate.getMonthValue() < 3) {
            year -= 1;
        }

        int century = parseCentury(year);
        int yearInTheCentury = parseYearInTheCentury(year);

        long result = Math.round(gregorianDate.getDayOfMonth() + 5 * century + yearInTheCentury +
                Math.floor((century / 4)) + Math.floor((yearInTheCentury / 4))
                + Math.floor((2.6 * parseMonth(gregorianDate.getMonthValue()) - 0.2)));

        return WeekDays.valueOf(Math.floorMod(result, 7));
    }

    public Map<LocalDateTime, WeekDays> getDaysOfWeek(List<LocalDateTime> dates) {
        Map<LocalDateTime, WeekDays> mapWithResults = new LinkedHashMap<LocalDateTime, WeekDays>(dates.size());
        
        for (int i = 0; i < dates.size(); i++)
            mapWithResults.put(dates.get(i), getDayOfWeek(dates.get(i)));

        return mapWithResults;
    }

    // Tests Done
    public LocalDateTime getGregorianDate(LocalDateTime date) {
        if (date.getYear() <= 0)
            throw new IllegalArgumentException("Year must be greater than 0.");

        if (dateIsGregorian(date)) return date;

        return parseJulianToGregorian(date);
    }

    // Tests Done
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
        //Conditional Value Mutant se n se testar o getdayoftheweek com 2
        if (month > 2) return month - 2;

        return month + 10;
    }

    private int parseCentury(int year) {
        return Math.floorDiv(year, 100);
    }

    private int parseYearInTheCentury(int year) {
        return year % 100;
    }

    public LocalDateTime parseJulianToGregorian(LocalDateTime date) {
        int centuryYear = parseCentury(date.getYear()) * 100;

        int toSubtract = 0;

        if (date.getYear() >= centuryYear) {
            centuryYear += 100;
        }

        // Equivalent Mutant
        // TODO conseguimos retirar se colocarmos como multiplo de 100
        /**
         * remove leap years of the Julian calendar
         * that are not in the Gregorian calendar
         */
        while (centuryYear <= 1500) {
            if (centuryYear % 400 != 0)
                toSubtract += 1;

            centuryYear += 100;
        }

        return date.plusDays(10 - toSubtract);
    }

}
