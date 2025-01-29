import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    protected String time;
    protected Optional<LocalDate> dueTime;

    /**
     * Creates an Event object with its description and time
     * @param description description
     * @param time time
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
        if(this.time.matches("\\d{4}-\\d{2}-\\d{2}")) { //YYYY-MM-DD
            this.dueTime = Optional.of(LocalDate.parse(this.time));
        }
    }

    /**
     *  Gives a string representation of the Event
     * @return String
     */
    @Override
    public String toString() {
        return this.dueTime
                .map(localDate -> "[E]" + super.toString() + String.format("(at: %s)",
                localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))).orElseGet(()
                        -> "[E]" + super.toString() + String.format("(at: %s)", this.time));
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile();
    }
}
