import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    protected String time;
    protected Optional<LocalDate> dueTime;
    protected boolean timePresent;

    /**
     * Creates an Event object with given description and time
     * @param description description
     * @param time time
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
        timePresent = false;
        if(this.time.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueTime = Optional.of(LocalDate.parse(this.time));
            timePresent = true;
        }
    }

    /**
     * Creates an Event object with its description, time and a specified completion status
     * @param description description
     * @param time time
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
        timePresent = false;
        if(this.time.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueTime = Optional.of(LocalDate.parse(this.time));
            timePresent = true;
        }
    }

    /**
     * Gives a string representation of the Event
     * @return String
     */
    @Override
    public String toString() {
        return this.dueTime
                .filter(t -> timePresent)  // Ensure time is present before formatting
                .map(localDate -> "[E]" + super.toString() +
                        String.format("(at: %s)", localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))))
                .orElseGet(() -> "[E]" + super.toString() + String.format("(at: %s)", this.time));
    }


    @Override
    public String toFile() {
        return "E | " + super.toFile();
    }
}
