package him.storage;


import him.task.Deadline;
import him.task.Event;
import him.task.Task;
import him.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }


    /**
     * Gets previously saved tasks from the specified file.
     *
     * @param filePath The file path where tasks are stored.
     * @return A list of tasks retrieved from the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public static ArrayList<Task> getPreviousTasks(String filePath) throws FileNotFoundException {
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

    /**
     * Converts a string representation of a boolean to an actual boolean value.
     *
     * @param value The string representation of a boolean.
     * @return {@code true} if the value is "1", otherwise {@code false}.
     */
    public static boolean getBooleanFromString(String value) {
        return !value.equals("0");
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void fillFileWithTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("data/data.txt");
        String accumulatedTasks = "";
        for (Task task : tasks) {
            accumulatedTasks = accumulatedTasks + task.toFile() + "\n";
        }
        fw.write(accumulatedTasks);
        fw.close();
    }
}
