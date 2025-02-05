package him.task;

/**
 * Represents a to-do task that does not have a specific deadline or event time.
 * Inherits from the {@code Task} class.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a To-Do object with its description.
     *
     * @param description description
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gives a string representation of the To-do object.
     *
     * @return a formatted String with its task type and its details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns a string representation of the ToDo task in a format suitable for file storage.
     *
     * @return A formatted string representing the task type, completion status, and description.
     */
    public String toFile() {
        return "T | " + super.toFile();
    }
}
