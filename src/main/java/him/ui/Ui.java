package him.ui;


import java.util.Scanner;

/**
 * Deals with interaction with the user by displaying messages and receiving input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs an Ui object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to the console.
     *
     * @param msg The message to be printed.
     */
    public static void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays the welcome message when the program starts.
     *
     * @return
     */
    public static String welcomeMsg() {
        print("Hello! I'm Him");
        print("How can I help you?");
        return null;
    }

    /**
     * Displays the farewell message when the program exits.
     */
    public String farewellMsg() {
        return "Glad to be of service";
    }

    /**
     * Displays an error message when the event description or time is missing.
     */
    public void eventErrorMsg() {
        print("The event description or time can't be empty bro, you gotta " +
                "use this format: event <task> at <time>");
    }

    /**
     * Displays an error message when the deadline description or due date is missing.
     */

    public void deadlineErrorMsg() {
        print("The deadline description or due date can't be empty bro, you gotta " +
                "use this format: deadline <task> by <date>");
    }

    /**
     * Reads and returns user input from the console.
     *
     * @return The user's input as a string.
     */
    public String getInput() {
        return this.scanner.nextLine();
    }
}
