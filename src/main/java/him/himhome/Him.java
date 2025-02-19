package him.himhome;

import him.parser.Parser;
import him.storage.Storage;
import him.task.Task;
import him.tasklist.TaskList;
import him.ui.Ui;

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

    public Him(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            tasks = new TaskList(Storage.getPreviousTasks(filePath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public Him() {
        this("data" + java.io.File.separator + "him.txt");
    }

    public String getResponse(String input) {
        ui.welcomeMsg();
        if (input == null) {
            return "Input cannot be null. Please provide a valid command.";
        }
        String output = "";
        if (input.equalsIgnoreCase("bye")) {
            output += ui.farewellMsg();
            try {
                Storage.fillFileWithTasks(tasks.getToDoList());
            } catch (IOException e) {
                output += "Error saving to file. Please check if 'him.txt' is present in '/him/'.";
            }
        } else if (input.startsWith("done")) {
            try {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                output = tasks.markDone(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                output += "Invalid input for 'done' command. Please provide a valid task index.";
            }
        } else if (input.startsWith("undone")) {
            try {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                output = tasks.unmarkDone(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                output += "Invalid input for 'undone' command. Please provide a valid task index.";
            }
        } else if (input.startsWith("delete")) {
            try {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                output = tasks.deleteTaskByIndex(index);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                output += "Invalid input for 'delete' command. Please provide a valid task index.";
            }
        } else if (input.equalsIgnoreCase("list")) {
            output = tasks.displayToDo();
        } else if (input.startsWith("find")) {
            String[] parsedInput = parser.parse(input, 2);
            output = tasks.findTask(parsedInput[1]);
        } else {
            String[] parsedInput = parser.parse(input, 2);
            if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                try {
                    String res = tasks.addToDo(parsedInput[0], parsedInput[1]);
                    output += res;
                } catch (ArrayIndexOutOfBoundsException e) {
                    output += "OOPS, task description cannot be empty.";
                }
            } else {
                output += "OOPS, I don't understand this input. Please use a known command and try again.";
            }
        }
        return output;
    }
}
