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
    public void addToDo(String todo, String description) {
        Task task = new Task("anything");
        if (todo.equals("event")) {
            try {
                String[] args = description.split("/", 2);
                task = new Event(args[0], args[1]);
                this.todos.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.eventErrorMsg();
            }
        } else if (todo.equals("deadline")) {
            try {
                String[] args = description.split("/", 2);
                task = new Deadline(args[0], args[1]);
                this.todos.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.deadlineErrorMsg();
            }
        } else {
            task = new ToDo(description);
            todos.add(task);
        }

        Ui.print("Gotcha bro! I've added this task:");
        Ui.print(todo.toString());
        System.out.printf("Now you have %d task(s) in your todo-list%n", todos.size());
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
    public void markDone(int index) {
        if (index > todos.size()) {
            Ui.print("Oops, I don't see that task. Please make sure its on the list!");
        } else {
            Ui.print("Congrats! Another task down: ");
            todos.get(index - 1).markAsDone();
            Ui.print(todos.get(index - 1).toString());
        }
    }

    /**
     * Displays all the tasks stored in the task list.
     */
    public void displayToDo() {
        Ui.print("Here are your tasks: ");
        int i = 1;
        for (Task todo : todos) {
            String res = String.format("%d. %s", i++, todo);
            Ui.print(res);
        }
    }

    /**
     * Deletes a task from the list by its index, otherwise will display error.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTaskByIndex(int index) {
        if (index > todos.size()) {
            Ui.print("Oops, I don't see that task. Please make sure its on the list!");
        } else {
            Task deleted = todos.get(index - 1);
            this.todos.remove(index - 1);
            Ui.print("No worries! I'll remove that for you: ");
            Ui.print(deleted.toString());
            Ui.print(String.format("Now you have %d task(s) in your todo-list", todos.size()));
        }
    }
}
