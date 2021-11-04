
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleGregorianCalendarTest {

    private SimpleGregorianCalendar gregorianCalendar;

    @BeforeEach
    void setUp() {
        this.gregorianCalendar = new SimpleGregorianCalendar();
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
}
