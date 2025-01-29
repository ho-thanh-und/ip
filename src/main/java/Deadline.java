import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Deadline extends Task {
    protected String by;
    protected Optional<LocalDate> dueDate;

    /**
     * Creates a Deadline object with its description and deadline
     * @param description description
     * @param by by
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.dueDate = parseDate(by);
    }

    /**
     * Parse a date String into Local Date if in the expected format
     * @param date date
     * @return an Optional
     */
    private Optional<LocalDate> parseDate(String date) {
        try {
            return Optional.of(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Gives a string representation of the Deadline object
     * @return formatted String with date if valid, or else just return a regular String
     */
    @Override
    public String toString() {
        return dueDate
                .map(localDate -> "[D]" + super.toString() + " (by: " +
                        localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")")
                .orElse("[D]" + super.toString() + " (by: " + by + ")");
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + dueDate.map(LocalDate::toString).orElse(by);
    }
}
