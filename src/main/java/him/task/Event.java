package him.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents an event task with a specific time or date.
 * The task can store the event time as a string or convert it into a LocalDate if formatted correctly.
 */
public class Event extends Task {
    private String startPeriod;
    private String endPeriod;
    private Optional<LocalDate> startDate;
    private Optional<LocalDate> endDate;
    private boolean hasValidDates;

    /**
     * Creates an Event task with a description and a time string in the format "<start> to <end>".
     *
     * @param description Description of the event.
     * @param periodStr   The time range in the form "<start> to <end>".
     */
    public Event(String description, String periodStr) {
        super(description);
        parsePeriod(periodStr);
    }

    /**
     * Creates an Event task with a description, period, and completion status.
     *
     * @param description Description of the event.
     * @param periodStr   The time range.
     * @param isDone      Completion status of the event.
     */
    public Event(String description, String periodStr, boolean isDone) {
        super(description, isDone);
        parsePeriod(periodStr);
    }

    /**
     * Splits the period string into startPeriod and endPeriod,
     * then tries to parse them as ISO dates.
     *
     * @param periodStr the period string in the format "<start> to <end>"
     */
    private void parsePeriod(String periodStr) {
        String[] parts = periodStr.split(" to ", 2);
        if (parts.length == 2) {
            this.startPeriod = parts[0].trim();
            this.endPeriod   = parts[1].trim();
        } else {
            this.startPeriod = periodStr;
            this.endPeriod   = "";
        }

        this.startDate = parseIsoDateSafe(startPeriod);
        this.endDate   = parseIsoDateSafe(endPeriod);
        this.hasValidDates = (startDate.isPresent() && endDate.isPresent());
    }

    /**
     * Parse an ISO date string into LocalDate.
     *
     * @param text the date string to parse
     * @return an Optional containing the parsed LocalDate, or empty if invalid
     */
    private Optional<LocalDate> parseIsoDateSafe(String text) {
        try {
            if (text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return Optional.of(LocalDate.parse(text));
            }
        } catch (Exception e) {
            // stub
        }
        return Optional.empty();
    }

    /**
     * Returns a string representation of the Event object.
     * The format varies depending on whether the event time is in a valid date format.
     *
     * @return A formatted string containing the task type, completion status, description, and event time.
     */
    @Override
    public String toString() {
        return startDate
                .filter(sd -> endDate.isPresent())
                .flatMap(sd -> endDate.map(ed -> {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    String startF = sd.format(fmt);
                    String endF   = ed.format(fmt);
                    return "[E]" + super.toString()
                            + String.format(" (from: %s to: %s)", startF, endF);
                }))
                .orElse("[E]" + super.toString()
                        + String.format(" (from: %s to: %s)", startPeriod, endPeriod));
    }

    /**
     * Returns a string representation of the Event object in a format suitable for file storage.
     *
     * @return A formatted string representing the event task for file storage.
     */
    @Override
    public String toFile() {
        String storedPeriod;
        if (hasValidDates) {
            // Both start and end are valid ISO dates
            storedPeriod = startDate.get().toString() + " to " + endDate.get().toString();
        } else {
            storedPeriod = startPeriod + " to " + endPeriod;
        }
        return "E | " + super.toFile() + " | " + storedPeriod;
    }
}
