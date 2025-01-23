import java.util.Scanner;

/**
 * The Ui class will help the chatbot [name still considering] interact
 * with users.
 */

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     *
     * @return a String which says Hi to the user.
     */
    public static String getWelcome() {
        return "Hello! I'm Him" + "\n" + "What can I do for you?";
    }

    /**
     *
     * @return a String which says Goodbye to the user.
     */

    public String getFarewell() {
        return "Bye. Hope to see you again soon!\n";
    }

}
