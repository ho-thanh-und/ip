package him.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    protected String by;
    protected Optional<LocalDate> dueDate;
    protected boolean dueDatePresent;

    /**
     * Creates a deadline object with given description and time to complete (by)
     * @param description description
     * @param by by
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
     * Create a deadline object with given description, time to complete (by)
     * and a specified completion status
     * @param description description
     * @param by by
     * @param isDone isDone
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

    //    private Optional<LocalDate> parseDate(String date) {
//        try {
//            return Optional.of(LocalDate.parse(date));
//        } catch (DateTimeParseException e) {
//            return Optional.empty();
//        }
//    }

    /**
     * Gives a string representation of the Deadline object
     * @return formatted String with date if valid, or else just return a regular String
     */
    @Override
    public String toString() {
        return dueDate
                .filter(d -> dueDatePresent)  // Ensure dueDate is present before formatting
                .map(localDate -> "[D]" + super.toString() +
                        String.format(" (by: %s)", localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))))
                .orElse("[D]" + super.toString() + String.format(" (by: %s)", by));
    }


    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + dueDate.map(LocalDate::toString).orElse(by);
    }
}
