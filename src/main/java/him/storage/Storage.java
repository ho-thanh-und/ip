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
    public String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }


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

    public static boolean getBooleanFromString(String s) {
        return !s.equals("0");
    }

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
