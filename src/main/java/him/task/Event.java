package him.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents an event task with a specific time or date.
 * The task can store the event time as a string or convert it into a LocalDate if formatted correctly.
 */
public class Event extends Task {
    protected String time;
    protected Optional<LocalDate> dueTime;
    protected boolean isTimePresent;

    /**
     * Creates an Event object with given description and time.
     *
     * @param description description of the event.
     * @param time time of the event in String format.
     */
    public Event(String description, String time) {
        super(description);
        assert !description.isEmpty() : "The Event description looks empty";
        this.time = time;
        isTimePresent = false;
        if(this.time.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueTime = Optional.of(LocalDate.parse(this.time));
            isTimePresent = true;
        } else {
            this.dueTime = Optional.empty();
        }
    }

    /**
     * Creates an Event object with its description, time and a specified completion status.
     *
     * @param description description of the event.
     * @param time time of the event in String format.
     * @param isDone isDone the completion status of the event.
     */
    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        assert !description.isEmpty() : "The Event description looks empty";
        this.time = time;
        isTimePresent = false;
        if(this.time.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.dueTime = Optional.of(LocalDate.parse(this.time));
            isTimePresent = true;
        } else {
            this.dueTime = Optional.empty();
        }
    }

    /**
     * Returns a string representation of the Event object.
     * The format varies depending on whether the event time is in a valid date format.
     *
     * @return A formatted string containing the task type, completion status, description, and event time.
     */
    @Override
    public String toString() {
        return this.dueTime
                .filter(t -> isTimePresent)  // Ensure time is present before formatting
                .map(localDate -> "[E]" + super.toString() +
                        String.format("(at: %s)", localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))))
                .orElseGet(() -> "[E]" + super.toString() + String.format("(at: %s)", this.time));
    }

    /**
     * Returns a string representation of the Event object in a format suitable for file storage.
     *
     * @return A formatted string representing the event task for file storage.
     */
    @Override
    public String toFile() {
        return "E | " + super.toFile();
    }
}
