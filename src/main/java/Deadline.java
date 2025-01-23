public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline object with its description and deadline
     * @param description description
     * @param by by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gives a string representation of the Deadline object
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
