package him.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    private ToDo todoNotDone;
    private ToDo todoDone;

    @BeforeEach
    void setUp() {
        todoNotDone = new ToDo("read a book");
        todoDone = new ToDo("submit assignment", true);
    }

    @Test
    void testToString_NotDone() {
        assertEquals("[T][ ] read a book", todoNotDone.toString(),
                "toString() output does not match expected format");
    }

    @Test
    void testToString_Done() {
        assertEquals("[T][X] submit assignment", todoDone.toString(),
                "toString() output does not match expected format");
    }

    @Test
    void testToFile_NotDone() {
        assertEquals("T | 0 | read a book", todoNotDone.toFile(),
                "toFile() output incorrect for not done task");
    }

    @Test
    void testToFile_Done() {
        assertEquals("T | 1 | submit assignment", todoDone.toFile(),
                "toFile() output incorrect for done task");
    }
}
