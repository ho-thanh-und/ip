public class Event extends Task {
    protected String time;

    /**
     * Creates an Event object with its description and time
     * @param description description
     * @param time time
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     *  Gives a string representation of the Event
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(at: %s)", this.time);
    }
}
