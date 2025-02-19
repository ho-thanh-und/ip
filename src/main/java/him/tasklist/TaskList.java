package him.tasklist;


import him.task.Deadline;
import him.task.Event;
import him.task.Task;
import him.task.ToDo;
import him.ui.Ui;

import java.util.ArrayList;

/**
 * Deals with the logic of tasks, including adding, retrieving, marking as done, deleting, and displaying tasks.
 */
public class TaskList {
    private ArrayList<Task> todos;
    private Ui ui = new Ui();

    /**
     * Constructs a TaskList object with a given list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.todos = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.todos = new ArrayList<Task>();
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getToDoByIndex(int index) {
        return this.todos.get(index);
    }

    /**
     * Adds a new task (ToDo, Deadline, or Event) to the task list.
     *
     * @param todo The type of task ("todo", "event", or "deadline").
     * @param description The description of the task, including date/time if applicable.
     */
    public String addToDo(String todo, String description) {
        Task task = new Task("anything");
        String output = "";
        if (todo.equals("event")) {
            try {
                String[] args = description.split(" at ", 2);
                task = new Event(args[0], args[1]);
                this.todos.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Oops, what's the task description?";
            }
        } else if (todo.equals("deadline")) {
            try {
                String[] args = description.split(" by ", 2);
                task = new Deadline(args[0], args[1]);
                this.todos.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Oops, what's the task description?";
            }
        } else {
            task = new ToDo(description);
            this.todos.add(task);
        }

        output += "Gotcha bro! I've added this task:\n";
        output += task.toString() + "\n";
        output += String.format("Now you have %d task(s) in your todo-list.\n", this.todos.size());
        return output;
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */
    public ArrayList<Task> getToDoList() {
        return this.todos;
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param index The index of the task to mark as done.
     */
    public String markDone(int index) {
        if (index > todos.size() || index < 0) {
            return "Oops, I don't see that task. Please make sure its on the list!";
        } else {
            String output = "Congrats! Another task down: ";
            todos.get(index - 1).markAsDone();
            output += todos.get(index - 1).toString() + "\n";
            return output;
        }
    }

    /**
     * Marks a task as incomplete by its index.
     */
    public String unmarkDone(int index) {
        if (index > todos.size() || index < 0) {
            return "Oops, I don't see that task. Please make sure its on the list!";
        } else {
            String output = "No worries, I'll unmark that for you! ";
            todos.get(index - 1).unmarkAsDone();
            output += todos.get(index - 1).toString() + "\n";
            return output;
        }
    }

    /**
     * Displays all the tasks stored in the task list.
     */
    public String displayToDo() {
        String output = "Here are your tasks:\n";
        int idx = 1;
        for (Task todo : todos) {
            output += String.format("%d. %s\n", idx, todo);
            idx++;
        }
        return output;
    }

    /**
     * Deletes a task from the list by its index, otherwise will display error.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTaskByIndex(int index) {
        if (index > todos.size()) {
            return "Oops, I don't see that task. Please make sure its on the list!";
        } else {
            Task deleted = todos.get(index - 1);
            this.todos.remove(index - 1);
            String output = "No worries! I'll remove that for you: ";
            output += deleted.toString() + "\n";
            output += String.format("Now you have %d task(s) in your todo-list", todos.size()) + "\n";
            return output;
        }
    }

    /**
     * Finds and displays tasks that match the given keyword in the description.
     *
     * @param keyword The keyword used to search.
     */
    public String findTask(String keyword) {
        int n = todos.size();
        String concat = "";
        int matches = 1;
        for (Task todo : todos) {
            if (todo.getDescription().contains(keyword)) {
                String representation = String.format("%d. %s", matches, todo);
                concat += representation + "\n";
                matches++;
            }
        }
        if (matches > 1) {
            concat = "Here are the matching tasks in your list:" + concat;
        } else {
            concat = "No tasks with given search keyword found. Please try again";
        }
        return concat;
    }
}
