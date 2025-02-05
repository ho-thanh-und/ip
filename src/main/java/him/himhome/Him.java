package him.himhome;

import him.parser.Parser;
import him.storage.Storage;
import him.task.Task;
import him.tasklist.TaskList;
import him.ui.Ui;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main entry point for the chatbot.
 * This class manages user interactions and task storage.
 */
public class Him {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ArrayList<Task> todo;

    /**
     * Default constructor for Him.
     * Initializes an empty task list.
     */
    public Him() {
        this.todo = new ArrayList<>();
    }

    /**
     * Initializes Him with a file path for storing tasks.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Him(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();

        try {
            tasks = new TaskList(Storage.getPreviousTasks(filePath));
        } catch (FileNotFoundException  e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop to handle user input.
     */
    public void run() {
        ui.welcomeMsg();
        try {
            while (true) {
                String input = ui.getInput();
                if (input.equalsIgnoreCase("bye")) {
                    ui.farewellMsg();
                    try {
                        Storage.fillFileWithTasks(tasks.getToDoList());
                    } catch (IOException e) {
                        Ui.print("Error saving to file, make sure 'data.txt' is present in '/data/'");
                    }
                    break;
                } else if (input.startsWith("done")) {
                    int index = Integer.parseInt(parser.parse(input, 2)[1]);
                    tasks.markDone(index);
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(parser.parse(input, 2)[1]);
                    tasks.deleteTaskByIndex(index);
                } else if (input.equalsIgnoreCase("list")) {
                    tasks.displayToDo();
                } else if (input.startsWith("find")) {
                    String keyword = input.substring(5).trim(); // Extract keyword
                    if (keyword.isEmpty()) {
                        Ui.print("Please provide a keyword");
                    } else {
                        tasks.findTask(keyword);
                    }
                } else {
                    String[] parsedInput = parser.parse(input, 2);
                    if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                        try {
                            tasks.addToDo(parsedInput[0], parsedInput[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            Ui.print("Oops, what's the task description?");
                        }
                    } else {
                        Ui.print("Oops, I don't know what this means.");
                    }
                }
            }
        } catch (java.util.NoSuchElementException e) {
            Ui.print("See you next time!");
        }
    }
    public static void main(String[] args) {
        Him him = new Him("data/him.txt");
        him.run();
    }
}

