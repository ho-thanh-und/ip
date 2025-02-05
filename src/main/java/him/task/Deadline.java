package him.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a task with a deadline. The task can store a due date in either string format or as a LocalDate object.
 */
public class Deadline extends Task {
    protected String by;
    protected Optional<LocalDate> dueDate;
    protected boolean dueDatePresent;

    /**
     * Creates a Deadline object with a given description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date in String format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        dueDatePresent = false;
        if (this.by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueDate = Optional.of(LocalDate.parse(this.by));
            dueDatePresent = true;
        } else {
            this.dueDate = Optional.empty();
        }
    }

    /**
     * Creates a Deadline object with a given description, deadline date, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline date in String format.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        dueDatePresent = false;
        if (this.by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueDate = Optional.of(LocalDate.parse(this.by));
            dueDatePresent = true;
        } else {
            this.dueDate = Optional.empty();
        }
    }

    /**
     * Returns a string representation of the Deadline object.
     * The format varies depending on whether the deadline is in a valid date format.
     *
     * @return A formatted string containing the task type, completion status, description, and deadline.
     */
    @Override
    public String toString() {
        return dueDate
                .filter(d -> dueDatePresent)  // Ensure dueDate is present before formatting
                .map(localDate -> "[D]" + super.toString() +
                        String.format(" (by: %s)", localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))))
                .orElse("[D]" + super.toString() + String.format(" (by: %s)", by));
    }

    /**
     * Returns a string representation of the Deadline object in a format suitable for file storage.
     *
     * @return A formatted string representing the deadline task for file storage.
     */
    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + dueDate.map(LocalDate::toString).orElse(by);
    }
}
