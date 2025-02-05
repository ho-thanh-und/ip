package him.tasklist;


import him.task.Deadline;
import him.task.Event;
import him.task.Task;
import him.task.ToDo;
import him.ui.Ui;

import java.util.ArrayList;

/**
 * Deals with the logic of tasks
 */
public class TaskList {
    private ArrayList<Task> todos;
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks) {
        this.todos = tasks;
    }

    public TaskList() {
        this.todos = new ArrayList<Task>();
    }

    public Task getToDoByIndex(int index) {
        return this.todos.get(index);
    }

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

    public ArrayList<Task> getToDoList() {
        return this.todos;
    }

    public void markDone(int index) {
        if (index > todos.size()) {
            Ui.print("Oops, I don't see that task. Please make sure its on the list!");
        } else {
            Ui.print("Congrats! Another task down: ");
            todos.get(index - 1).markAsDone();
            Ui.print(todos.get(index - 1).toString());
        }
    }

    /*
    Displays all the stored tasks on the system output
     */
    public void displayToDo() {
        Ui.print("Here are your tasks: ");
        int i = 1;
        for (Task todo : todos) {
            String res = String.format("%d. %s", i++, todo);
            Ui.print(res);
        }
    }

    /*
    Deletes the task at specified index if it exists, otherwise will display error
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
