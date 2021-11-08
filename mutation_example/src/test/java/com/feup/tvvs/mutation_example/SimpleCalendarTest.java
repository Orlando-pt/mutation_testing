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

    /**
     * Exemplo 1 Conditional Boundary Mutator - Linha 19 e linha 56
     * Se for data juliana, ainda se vão somar dias, se se colocar dia 28 é possível que passe para o mês 4
     *
     * @Test void testGetDayOfWeekWhen1Mar1400ShouldReturnMonday() {
     * assertEquals(
     * WeekDays.WEDNESDAY,
     * this.gregorianCalendar.getDayOfWeek(
     * LocalDateTime.of(1600, 3, 1, 0, 0)
     * )
     * );
     * }
     *
     * @Test void testGetDayOfWeekWhen1Mar1400ShouldReturnMonday() {
     *      * assertEquals(
     *      * WeekDays.TUESDAY,
     *      * this.gregorianCalendar.getDayOfWeek(
     *      * LocalDateTime.of(1600, 2, 1, 0, 0)
     *      * )
     *      * );
     *      * }
     *
     */


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
