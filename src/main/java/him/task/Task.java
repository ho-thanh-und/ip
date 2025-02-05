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
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create a task and allows the user to set to whether or not its complete.
     *
     * @param description description of the task.
     * @param isDone is defined by its completion status.
     */
    public Task(String description, boolean isDone) {
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
