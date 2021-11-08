package com.feup.tvvs.mutation_example;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

public class SimpleCalendarTest {

    private SimpleCalendar gregorianCalendar;

    @BeforeEach
    void setUp() {
        this.gregorianCalendar = new SimpleCalendar();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * ----------------------------- *
     * getDayOfWeek TESTS      *
     * ----------------------------- *
     */

    @Test
    void testGetDayOfWeekWhen24Jan1988ShouldReturnSunday() {
        assertEquals(
                WeekDays.SUNDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1988, 1, 24, 0, 0)

                )
        );
    }

    @Test
    void testGetDayOfWeekWhen4Nov2021ShouldReturnThursday() {
        assertEquals(
                WeekDays.FRIDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(2021, 2, 12, 0, 0)
                )
        );
    }

    /**
     * getDaysOfWeek TESTS
     */
    @Test
    void testGetDaysOfWeekWithValidDates() {
        LocalDateTime friday = LocalDateTime.of(2021, 11, 12, 0, 0, 0);
        LocalDateTime saturday = LocalDateTime.of(2021, 11, 13, 0, 0, 0);
        LocalDateTime sunday = LocalDateTime.of(2021, 11, 14, 0, 0, 0);

        List<LocalDateTime> dates = List.of(friday, saturday, sunday);

        var expectedResults = new LinkedHashMap<>(dates.size());
        expectedResults.put(friday, WeekDays.FRIDAY);
        expectedResults.put(saturday, WeekDays.SATURDAY);
        expectedResults.put(sunday, WeekDays.SUNDAY);

        assertEquals(
                expectedResults,
                this.gregorianCalendar.getDaysOfWeek(dates)
        );        
    }

    // test this method when it gets invalid date

    /**
     * testing Julian Date
     * Legend has it that leonardo da vinci was born
     * on a beautiful Saturday morning of April 15, 1452
     * Let's see if this is true
     */
    @Test
    void testGetDayOfWeekWhen15Apr1452ShouldReturnSaturday() {
        assertEquals(
                WeekDays.SATURDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1452, 4, 15, 0, 0)
                )
        );
    }


    @Test
    void testGetDayOfWeekWhen1Mar1400ShouldReturnMonday() {
        assertEquals(
                WeekDays.MONDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1400, 3, 1, 0, 0)
                )
        );
    }

    @Test
    void testGetDayOfWeekWhen1Jan0001ShouldReturnSaturday() {
        assertEquals(
                WeekDays.SATURDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1, 1, 1, 0, 0)
                )
        );
    }

    /**
     * ----------------------------- *
     * getGregorianDate TESTS      *
     * ----------------------------- *
     */

    @Test
    void testGetGregorianDateWhenYearIsLessThanZeroShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    this.gregorianCalendar.getGregorianDate(LocalDateTime.of(-1, 1, 1, 0,
                            0));
                },
                "Year must be greater than 0.");
    }

    @Test
    void testGetGregorianDateWhenYearIsZeroShouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    this.gregorianCalendar.getGregorianDate(LocalDateTime.of(0, 1, 1, 0,
                            0));
                },
                "Year must be greater than 0.");
    }

    /**
     * ----------------------------- *
     * dateIsGregorian TESTS      *
     * ----------------------------- *
     */

    @Test
    void testDateIsGregorianWhenDateAfterChangeShouldReturnTrue() {
        assertTrue(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 10, 5, 0, 0)
                )
        );
    }

    @Test
    void testDateIsGregorianWhenMonth10AndYear1582AndDayLessThan5ShouldReturnFalse() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 10, 4, 0, 0)
                )
        );
    }

    @Test
    void testDateIsGregorianWhenMonthLessThan10AndYear1582ShouldReturnFalse() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 9, 4, 0, 0)
                )
        );
    }

    @Test
    void testDateIsGregorianWhenYearLessThan1582ShouldReturnFalse() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1300, 9, 4, 0, 0)
                )
        );
    }

    /**
     * ----------------------------- *
     * parseJulianToGregorian TESTS      *
     * ----------------------------- *
     */

    // TODO
    /**
     * temos de adicionar estes testes quando passamos a utilizar o segundo
     * grupo de operadores
     */
    
    @Test
    void killMutatorThatReplacesFirstConditionOnDateIsGregorianMethod() {
        assertTrue(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1583, 10, 3, 0, 0)
                )
        );
    }

    @Test
    void killMutatorThatReplacesSecondConditionOnDateIsGregorianMethod() {
        assertTrue(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 11, 3, 0, 0)
                )
        );
    }

    @Test
    void test1() {
        assertEquals(
                10,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1352, 1, 2,5, 5))
                        .getDayOfMonth()
        );
    }
    /**
     * fim dos testes adicionais
     */

    @Test
    void testParseJulianToGregorianWhenYearIsTwoCenturyDatesNotDivisibleBy400AwayOf1582ShouldAdd8() {
        assertEquals(
                10,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1352, 1, 2,5, 5))
                        .getDayOfMonth()
        );
    }

    @Test
    void testParseJulianToGregorianWhenYearHaveNoCenturyDatesAwayOf1582ShouldAdd10() {
        assertEquals(
                12,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1552, 1, 2,5, 5))
                        .getDayOfMonth()
        );
    }

    @Test
    void testParseJulianToGregorianWhenYearHaveFourCenturyDatesAwayOf1582AndOneBeingDivisibleBy400ShouldAdd7() {
        assertEquals(
                9,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1200, 1, 2,5, 5))
                        .getDayOfMonth()
        );
    }
}
