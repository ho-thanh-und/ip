public class ToDo extends Task {
    /**
     * Creates a To-Do object with its description
     * @param description description
     */
    public ToDo(String description, boolean isDone) {

        super(description, isDone);
    }

    /**
     * Gives a string representation of the To-do object
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFile() {
        return "T | " + super.toFile();
    }
}
