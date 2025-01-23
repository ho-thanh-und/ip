public class ToDo extends Task {
    /**
     * Creates a To-Do object with its description
     * @param description description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gives a string representation of the To-do object
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
