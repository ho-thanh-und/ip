import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Him {
    private ArrayList<Task> todos;

    public Him() {
        this.todos = new ArrayList<>();
    }

    public static void print(String msg) {
        System.out.println(msg);
    }

    public void displayTodo() {
        print("Here are your tasks: ");
        int i = 1;
        for (Task todo : todos) {
            String res = System.out.printf("%d. %s%n", i++, todo).toString();
            print(res);
        }
    }

    public void markDone(int index) {
        print("Congrats! Another task down: ");
        todos.get(index - 1).markAsDone();
        print(todos.get(index - 1).toString());
    }

    public void addToDo(String[] words, String input) throws BlankToDoException {
        if (words.length == 1) {
            throw new BlankToDoException("Oh dear! The todo seems to be empty");
        }
        Task todo = new ToDo(input.substring(5), false);
        this.todos.add(todo);
        print("Gotcha bro! I've added this task:");
        print(todo.toString());
        System.out.printf("Now you have %d task(s) in your todo-list%n", todos.size());
    }

    public void addDeadline(String[] words, String input) throws BlankDeadlineException {
        if (words.length < 4 || !input.contains(" by ")) {
            throw new BlankDeadlineException("The deadline description or due date can't be empty bro, you gotta " +
                    "use this format: deadline <task> by <date>");
        }

        String[] parts = input.split(" by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new BlankDeadlineException("The deadline description or due date can't be empty bro, you gotta " +
                    "use this format: deadline <task> by <date>");
        }

        String description = parts[0].substring(9).trim();  // Remove "deadline "
        String dueDate = parts[1].trim();

        if (description.isEmpty()) {
            throw new BlankDeadlineException("The deadline description or due date can't be empty bro, you gotta " +
                    "use this format: deadline <task> by <date>");
        }

        Task deadline = new Deadline(description, dueDate, false);
        this.todos.add(deadline);
        print("Gotcha bro! I've added this deadline:");
        print(deadline.toString());
        System.out.printf("Now you have %d deadline(s) in your todo-list%n", todos.size());
    }

    public void addEvent(String[] words, String input) throws BlankEventException {
        if (words.length < 4 || !input.contains(" at ")) {
            throw new BlankEventException("The event description or time can't be empty bro, you gotta " +
                    "use this format: event <task> at <time>");
        }

        String[] parts = input.split(" at ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new BlankEventException("The event description or time can't be empty bro, you gotta " +
                    "use this format: event <task> at <time>");
        }

        String description = parts[0].substring(6).trim();  // Remove "event "
        String eventTime = parts[1].trim();

        if (description.isEmpty()) {
            throw new BlankEventException("The event description or time can't be empty bro, you gotta " +
                    "use this format: event <task> at <time>");
        }

        Task event = new Event(description, eventTime, false);
        this.todos.add(event);
        print("Gotcha bro! I've added this event:");
        print(event.toString());
        System.out.printf("Now you have %d event(s) in your todo-list%n", todos.size());
    }


    public void deleteToDo(int index) {
        Task deleted = todos.get(index - 1);
        this.todos.remove(index - 1);
        print("No worries! I'll remove that for you: ");
        print(deleted.toString());
        System.out.printf("Now you have %d task(s) in your todo-list%n", todos.size());
    }

    public static ArrayList<Task> getPreviousTasks() throws FileNotFoundException {
        File file = new File("data/data.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] data = line.split(" \\| ");
            Task task;
            switch (data[0]) {
                case "T":
                    task = new ToDo(data[2], getBooleanFromString(data[1]));
                    tasks.add(task);
                    break;
                case "D":
                    task = new Deadline(data[2], data[3], getBooleanFromString(data[1]));
                    tasks.add(task);
                    break;
                case "E":
                    task = new Event(data[2], data[3], getBooleanFromString(data[1]));
                    tasks.add(task);
                    break;
            }
        }
        return tasks;
    }

    public static boolean getBooleanFromString(String s) {
        return !s.equals("0");
    }

    public static void fillFileWithTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("data/data.txt");
        String accumulatedTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            accumulatedTasks = accumulatedTasks + tasks.get(i).toFile() + "\n";
        }
        fw.write(accumulatedTasks);
        fw.close();
    }

    public static void main(String[] args) {
        Him him = new Him();

        try {
            him.todos = getPreviousTasks();
        } catch (FileNotFoundException e) {
            // Ignore file not found case, just start with an empty task list
        }

        print("Hello! I'm Him");
        print("How can I help you?");

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String input = scanner.nextLine();
                String[] words = input.split(" ");
                String fstWord = words[0];

                if (input.equals("thank you")) {
                    print("You're welcome, kind individual!");
                    try {
                        fillFileWithTasks(him.todos);
                    } catch (IOException e) {
                        print("Unable to save the file 'data.txt'");
                    }
                    break;
                } else if (input.equals("list")) {
                    him.displayTodo();
                } else if (fstWord.equals("done")) {
                    int num = Integer.parseInt(words[1]);
                    him.markDone(num);
                } else if (fstWord.equals("todo")) {
                    try {
                        him.addToDo(words, input);
                    } catch (BlankToDoException e) {
                        print(e.getMessage());
                    }
                } else if (fstWord.equals("deadline")) {
                    try {
                        him.addDeadline(words, input);
                    } catch (BlankDeadlineException e) {
                        print(e.getMessage());
                    }
                } else if (fstWord.equals("event")) {
                    try {
                        him.addEvent(words, input);
                    } catch (BlankEventException e) {
                        print(e.getMessage());
                    }
                } else if (fstWord.equals("delete")) {
                    int deleteNum = Integer.parseInt(words[1]);
                    him.deleteToDo(deleteNum);
                } else {
                    print("Sorry, I don't know what that means!");
                }
            }
        } catch (NoSuchElementException e) {
            // Handle Ctrl+C gracefully
            print("Glad to have helped!");
        } finally {
            scanner.close();
        }
    }
}

