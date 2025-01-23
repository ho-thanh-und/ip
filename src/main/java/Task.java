public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with its description and is set to false as default
     * @param description is the description of the task (input from user)
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the icon of the task
     * @return a String which stores the status icons
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task completed by setting the boolean variable isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks tasks that were previously marked as completed
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
