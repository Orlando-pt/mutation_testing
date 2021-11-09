package com.feup.tvvs.mutation_example;

import java.time.LocalDateTime;

public class SimpleCalendar {

    /**
     * Date that the calendar changed from Julian to Gregorian
     */
    private final static int YEAR_OF_CHANGE = 1582;
    private final static int MONTH_OF_CHANGE = 10;
    private final static int DAY_OF_CHANGE = 4;

    /**
     * for more information regarding the formula used visit
     * https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week#Disparate_variation
     * 
     * @param date
     * @returns the day of the week correspondent to the specified date
     */
    public WeekDays getDayOfWeek(LocalDateTime date) {
        LocalDateTime gregorianDate = getGregorianDate(date);

        int year = gregorianDate.getYear();

        if (gregorianDate.getMonthValue() < 3) {
            year -= 1;
        }

        int century = parseCentury(year);
        int yearInTheCentury = parseYearInTheCentury(year);

        long result = Math.round(gregorianDate.getDayOfMonth() +
                Math.floor(2.6 * parseMonth(gregorianDate.getMonthValue()) - 0.2) + yearInTheCentury +
                Math.floor(yearInTheCentury / 4) + Math.floor(century / 4) - 2 * century);

        return WeekDays.valueOf(Math.floorMod(result, 7));
    }

    /**
     * when the user passes a julian date to this method
     * it returns the matching gregorian date
     * 
     * if date is already gregorian
     * it returns the same date
     * 
     * @param date
     * @returns gregorian date
     */
    public LocalDateTime getGregorianDate(LocalDateTime date) {
        if (date.getYear() <= 0)
            throw new IllegalArgumentException("Year must be greater than 0.");

        if (dateIsGregorian(date)) return date;

        return parseJulianToGregorian(date);
    }

    /**
     * verifies if the @param date is a gregorian date
     * 
     * @returns true if it is, false if it is not
     */
    public boolean dateIsGregorian(LocalDateTime date) {
        if (date.getYear() < YEAR_OF_CHANGE)
            return false;

        if (date.getYear() == YEAR_OF_CHANGE && date.getMonthValue() < MONTH_OF_CHANGE)
            return false;

        if (date.getYear() == YEAR_OF_CHANGE && date.getMonthValue() == MONTH_OF_CHANGE && date.getDayOfMonth() <= DAY_OF_CHANGE)
            return false;

        return true;
    }

    /**
     * Shifts months to be in accordance to the Disparate Variation algorithm guidelines
     * 
     * In Disparate Variation algorithm, the months are numbered from 1 for March to 12 for February
     * 
     * @param month = 3
     * @return 1
     */
    private int parseMonth(int month) {
        if (month > 2) return month - 2;

        return month + 10;
    }

    /**
     * Get param C in the Disparate Variation algorithm
     * 
     * @param year = 1521
     * @return 15
     */
    private int parseCentury(int year) {
        return Math.floorDiv(year, 100);
    }

    /**
     * Get param Y in the Disparate Variation algorithm
     * @param year = 1521
     * @return 21
     */
    private int parseYearInTheCentury(int year) {
        return year % 100;
    }

    /**
     * Parse Julian date to Gregorian date
     * 
     * this algorithm adds 10 days
     * and subtracts one day for each century not divisible by 400
     * between @param date and 1582
     * @param date 1421
     * @return @param date + 10 days - 1 day (1500 not divisible by 400)
     */
    public LocalDateTime parseJulianToGregorian(LocalDateTime date) {
        int centuryYear = parseCentury(date.getYear()) * 100;

        int toSubtract = 0;

        if (date.getYear() > centuryYear || date.getYear() == centuryYear && date.getMonthValue() >= 3) {
            centuryYear += 100;
        }

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
