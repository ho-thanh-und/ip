package him.himhome;

import him.parser.CommandType;
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

        StringBuilder outputBuilder = new StringBuilder();
        CommandType commandType = parser.parseCommand(input);

        switch (commandType) {
        case BYE:
            outputBuilder.append(ui.farewellMsg());
            try {
                Storage.fillFileWithTasks(tasks.getToDoList());
            } catch (IOException e) {
                outputBuilder.append("Error saving to file. Please check if 'him.txt' is present in '/him/'.");
            }
            break;

        case DONE:
            try {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                outputBuilder.append(tasks.markDone(index));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                outputBuilder.append("Invalid input for 'done' command. Please provide a valid task index.");
            }
            break;

        case UNDONE:
            try {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                outputBuilder.append(tasks.unmarkDone(index));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                outputBuilder.append("Invalid input for 'undone' command. Please provide a valid task index.");
            }
            break;

        case DELETE:
            try {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                outputBuilder.append(tasks.deleteTaskByIndex(index));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                outputBuilder.append("Invalid input for 'delete' command. Please provide a valid task index.");
            }
            break;

        case LIST:
            outputBuilder.append(tasks.displayToDo());
            break;

        case FIND:
            try {
                String[] parsedInput = parser.parse(input, 2);
                outputBuilder.append(tasks.findTask(parsedInput[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                outputBuilder.append("Please provide a keyword after 'find'.");
            }
            break;

        case ADD_TASK:
            // Covers "todo", "deadline", "event", and "dowithin"
            try {
                String[] parsedInput = parser.parse(input, 2);
                String res = tasks.addToDo(parsedInput[0], parsedInput[1]);
                outputBuilder.append(res);
            } catch (ArrayIndexOutOfBoundsException e) {
                outputBuilder.append("OOPS, task description cannot be empty.");
            }
            break;

        case UNKNOWN:
        default:
            outputBuilder.append("OOPS, I don't understand this input. Please use a known command and try again.");
            break;
        }

        return outputBuilder.toString();
    }
}

