package him.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a task that must be done within a certain period, e.g.:
 * "collect certificate between Jan 15 and Jan 25."
 */
public class DoWithin extends Task {

    private String startPeriod;
    private String endPeriod;

    private Optional<LocalDate> startDate;
    private Optional<LocalDate> endDate;
    private boolean hasValidDates;

    /**
     * Creates a DoWithin task with a description and a period (start + end).
     *
     * @param description Description of the task to be done.
     * @param periodStr   The period in the form "start and end", e.g. "2025-01-15 and 2025-01-25".
     */
    public DoWithin(String description, String periodStr) {
        super(description);
        parsePeriod(periodStr);
    }

    /**
     * Creates a DoWithin task with a description, period, and completion status.
     *
     * @param description Description of the task to be done.
     * @param periodStr   The period in the form "start and end".
     * @param isDone      Completion status of the task.
     */
    public DoWithin(String description, String periodStr, boolean isDone) {
        super(description, isDone);
        parsePeriod(periodStr);
    }

    /**
     * Helper method to split the period into startPeriod and endPeriod,
     * parse them as LocalDate if possible, then set hasValidDates accordingly.
     */
    private void parsePeriod(String periodStr) {
        String[] parts = periodStr.split(" and ", 2);
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
     * Tries to parse an ISO date string into LocalDate
     */
    private Optional<LocalDate> parseIsoDateSafe(String text) {
        try {
            if (text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return Optional.of(LocalDate.parse(text));
            }
        } catch (Exception e) {
            //stub
        }
        return Optional.empty();
    }

    /**
     * Returns a string representation of the DoWithin object.
     *
     * @return A formatted string containing the task type, completion status, description, and duration of completion.
     */
    @Override
    public String toString() {
        return startDate
                .filter(sd -> endDate.isPresent())
                .flatMap(sd -> endDate.map(ed -> {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    String startF = sd.format(fmt);
                    String endF   = ed.format(fmt);
                    return "[W]" + super.toString()
                            + String.format(" (between: %s and %s)", startF, endF);
                }))
                .orElse("[W]" + super.toString()
                        + String.format(" (between: %s and %s)", startPeriod, endPeriod));
    }

    /**
     * Returns a string representation of the DoWithin object in a format suitable for file storage.
     *
     * @return A formatted string representing the dowithin task for file storage.
     */
    @Override
    public String toFile() {
        String storedPeriod;
        if (hasValidDates) {
            storedPeriod = startDate.get().toString() + " and " + endDate.get().toString();
        } else {
            storedPeriod = startPeriod + " and " + endPeriod;
        }

        return "W | " + super.toFile() + " | " + storedPeriod;
    }
}
