package him.task;

/**
 * Represents a task with a description and a completion status.
 * This serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with its description and is set to false as default.
     *
     * @param description is the description of the task.
     */
    public Task(String description) {
        assert description.isEmpty() : "The ToDo description for the user looks empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description A brief description of the task.
     * @param isDone      Whether the task is initially marked as complete (true) or not (false).
     */
    public Task(String description, boolean isDone) {
        assert description.isEmpty() : "The ToDo description in the file looks empty";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the icon of the task.
     *
     * @return a String which stores the status icons.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task completed by setting the boolean variable isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks tasks that were previously marked as completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the task's description.
     *
     * @return The task description as a string.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns a String representation of the task.
     *
     * @return a formatted String containing the status icon and the description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     *
     * @return A formatted string representing the task's completion status and description.
     */
    public String toFile() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }
}
