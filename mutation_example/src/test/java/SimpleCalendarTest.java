import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                        new SimpleDate(5, 10, 1582)
                )
        );
    }

    @Test
    void julianDateShouldReturnFalse() {
        assertFalse(
                this.gregorianCalendar.dateIsGregorian(
                        new SimpleDate(4, 10, 1582)
                )
        );
    }

    @Test
    void getDayOfWeek24Jan1988ShouldReturnSunday() {
        assertEquals(
                this.gregorianCalendar.getDayOfWeek(
                        new SimpleDate(24, 1, 1988)
                ), WeekDays.SUNDAY
        );
    }

    @Test
    void getDayOfWeek15Apr1452ShouldReturnSaturday() {
        assertEquals(
                this.gregorianCalendar.getDayOfWeek(
                        new SimpleDate(15, 4, 1452)
                ), WeekDays.SATURDAY
        );
    }
}
