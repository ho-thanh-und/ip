import java.util.ArrayList;
import java.util.Scanner;

public class Him {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Him");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> todos = new ArrayList<>();

        /*
          While loop is used for the echoing functionality:
          - What the user inputs, the program outputs
          - If it encounters a "bye", then the program will exit
         */
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("thank you")) {
                System.out.println("You're welcome, kind individual!");
                break;
                /*
                - Used ArrayList for adjustable array size
                 */
            } else if (input.equals("list")) {
                int i = 1;
                for (String todo : todos) {
                    System.out.printf("%d. %s%n", i++, todo);
                }
            } else {
                todos.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
