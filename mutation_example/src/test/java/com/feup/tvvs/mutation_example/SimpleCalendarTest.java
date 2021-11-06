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
    void tearDown() {}

    @Test
    void gregorianDateShouldReturnTrue() {
        assertTrue(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 10, 5, 0, 0)
                )
        );
    }

    @Test
    void julianDateShouldReturnFalse() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 10, 4, 0, 0)
                )
        );
    }

    @Test
    void julianDateShouldReturnFalse2() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1582, 9, 4, 0, 0)
                )
        );
    }

    @Test
    void julianDateShouldReturnFalse3() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        LocalDateTime.of(1300, 9, 4, 0, 0)
                )
        );
    }

    @Test
    void getDayOfWeek24Jan1988ShouldReturnSunday() {
        assertEquals(
                WeekDays.SUNDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1988, 1, 24, 0, 0)

                )
        );
    }

    @Test
    void getDayOfWeek4Nov2021ShouldReturnThursday() {
        assertEquals(
                WeekDays.FRIDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(2021, 11, 12, 0, 0)
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
    void getDayOfWeek15Apr1452ShouldReturnSaturday() {
        assertEquals(
                WeekDays.SATURDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1452, 4, 15, 0, 0)
                )
        );
    }

    /**
     * test when working with the year 1400
     * 1 january 1400 was a Thursday
     */
    @Test
    void check1January1400IsThursday() {
        assertEquals(
                WeekDays.THURSDAY,
                this.gregorianCalendar.getDayOfWeek(
                        LocalDateTime.of(1400, 1, 1, 0,0)
                )
        );
    }

    /**
     * Test parseJulianToGregorian() method
     */
    @Test
    void april15Of1452IsJulianDateSoShouldReturnGregorianDate() {
        var date = LocalDateTime.of(1452, 4, 15, 0, 0);
        var dateAfterTransformation = LocalDateTime.of(1452, 4, 24, 0, 0);

        assertEquals(
                dateAfterTransformation,
                this.gregorianCalendar.getGregorianDate(date)
        );
    }

    @Test
    void january011400ShouldDecrement2WhenConvertingToGregorianCalendar() {
        var date = LocalDateTime.of(1400, 1, 1, 0, 0);
        var dateAfterTransformation = LocalDateTime.of(1400, 1, 9, 0, 0);

        assertEquals(
                dateAfterTransformation,
                this.gregorianCalendar.getGregorianDate(date)
        );
    }

    /**
     * TODO testar com um dia de 1200 ou 1400
     */
}
