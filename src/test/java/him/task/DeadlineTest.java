package him.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline deadlineNormal;
    private Deadline deadlineDate;
    private Deadline deadlineCompleted;

    @BeforeEach
    void setUp() {
        deadlineNormal = new Deadline("submit report", "next Monday");
        deadlineDate = new Deadline("submit project", "2025-03-15");
        deadlineCompleted = new Deadline("finish essay", "2025-06-20", true);
    }

    @Test
    void testToString_RegularStringDeadline() {
        assertEquals("[D][ ] submit report (by: next Monday)", deadlineNormal.toString(),
                "toString() output does not match expected format");
    }

    @Test
    void testToString_ValidDateDeadline() {
        assertEquals("[D][ ] submit project (by: Mar 15 2025)", deadlineDate.toString(),
                "toString() output incorrect for valid date format");
    }

    @Test
    void testToString_CompletedDeadline() {
        assertEquals("[D][X] finish essay (by: Jun 20 2025)", deadlineCompleted.toString(),
                "toString() output incorrect for completed deadline");
    }

    @Test
    void testToFile_RegularStringDeadline() {
        assertEquals("D | 0 | submit report | next Monday", deadlineNormal.toFile(),
                "toFile() output incorrect for non-date deadline");
    }

    @Test
    void testToFile_ValidDateDeadline() {
        assertEquals("D | 1 | finish essay | 2025-06-20", deadlineCompleted.toFile(),
                "toFile() output incorrect for valid date deadline");
    }
}

