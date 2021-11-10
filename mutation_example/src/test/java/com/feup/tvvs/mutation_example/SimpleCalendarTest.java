package com.feup.tvvs.mutation_example;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
     * Tests to complete in practical class
     */

    // Exercise 1
    @Test
    void testToKillMutantOne() {
        // TODO
    }

    @Test
    void testToKillMutantTwo() {
        // TODO
    }

    @Test
    void testToKillMutantThree() {
        // TODO
    }

    // End exercise 1

    // Exercise 2
    @Test
    void testToKillMutantFour() {
        // TODO
    }

    @Test
    void testToKillMutantFive() {
        // TODO
    }

    // End exercise 2

    // Exercise 3
    @Test
    void testToKillMutantSix() {
        // TODO
    }

    // End exercise 3

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

    @Test
    void testParseJulianToGregorianWhenDateIsJulianThenShouldReturnGregorianDate() {
        assertEquals(
                LocalDateTime.of(1400, 1, 9, 0, 0, 0),
                this.gregorianCalendar.parseJulianToGregorian(
                        LocalDateTime.of(1400, 1, 1, 0, 0, 0)
                )
        );
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

    @Test
    void testParseJulianToGregorianWhenYearIsTwoCenturyDatesNotDivisibleBy400AwayOf1582ShouldAdd8() {
        assertEquals(
                10,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1352, 1, 2, 5, 5))
                        .getDayOfMonth()
        );
    }

    @Test
    void testParseJulianToGregorianWhenYearHaveNoCenturyDatesAwayOf1582ShouldAdd10() {
        assertEquals(
                12,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1552, 1, 2, 5, 5))
                        .getDayOfMonth()
        );
    }

    @Test
    void testParseJulianToGregorianWhenYearHaveFourCenturyDatesAwayOf1582AndOneBeingDivisibleBy400ShouldAdd7() {
        assertEquals(
                9,
                this.gregorianCalendar
                        .parseJulianToGregorian(LocalDateTime.of(1200, 1, 2, 5, 5))
                        .getDayOfMonth()
        );
    }
}
