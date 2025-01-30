import java.util.Scanner;
/**
 * Deals with interaction with the user
 */

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void print(String msg) {
        System.out.println(msg);
    }

    public void welcomeMsg() {
        print("Hello! I'm Him");
        print("How can I help you?");
    }

    public void farewellMsg() {
        print("Glad to be of service");
    }

    public void eventErrorMsg() {
        print("The event description or time can't be empty bro, you gotta " +
                "use this format: event <task> at <time>");
    }

    public void deadlineErrorMsg() {
        print("The deadline description or due date can't be empty bro, you gotta " +
                "use this format: deadline <task> by <date>");
    }

    public String getInput() {
        return this.scanner.nextLine();
    }
}
