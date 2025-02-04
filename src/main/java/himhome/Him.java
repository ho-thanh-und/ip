package himhome;

import him.parser.Parser;
import him.storage.Storage;
import him.task.Task;
import him.tasklist.TaskList;
import him.ui.Ui;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Him {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ArrayList<Task> todo;

    public Him() {
        this.todo = new ArrayList<>();
    }

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

    public void run() {
        ui.welcomeMsg();
        while (true) {
            String input = ui.getInput();
            if(input.equalsIgnoreCase("bye")) {
                ui.farewellMsg();
                try {
                    Storage.fillFileWithTasks(tasks.getToDoList());
                } catch (IOException e) {
                    ui.print("Error saving to file, make sure 'data.txt' is present in '/data/");
                }
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                tasks.markDone(index);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(parser.parse(input, 2)[1]);
                tasks.deleteTaskByIndex(index);
            } else if (input.equalsIgnoreCase("list")) {
                tasks.displayToDo();
            } else {
                String[] parsedInput = parser.parse(input, 2);
                if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    try {
                        tasks.addToDo(parsedInput[0], parsedInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.print("Oops, whats the task description?");
                    }
                } else {
                    Ui.print("Oops, I don't know what this means");
                }
            }
        }
    }
    public static void main(String[] args) {
        Him him = new Him("data/him.txt");
        him.run();
    }

}

