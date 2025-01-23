import java.util.ArrayList;
import java.util.Scanner;

public class Him {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Him");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> todos = new ArrayList<>();

        /*
          While loop is used for the echoing functionality:
          - What the user inputs, the program outputs
          - If it encounters a "bye", then the program will exit
         */
        while (true) {
            String input = scanner.nextLine();
            String fstWord = input.split(" ")[0];
            if (input.equals("thank you")) {
                System.out.println("You're welcome, kind individual!");
                break;
                /*
                - Used ArrayList for adjustable array size
                 */
            } else if (input.equals("list")) {
                int i = 1;
                for (Task todo : todos) {
                    System.out.printf("%d. %s%n", i++, todo);
                }
            } else if (fstWord.equals("mark")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                Task task = todos.get(num - 1);
                task.markAsDone();
                System.out.println("Congrats! Another task down: ");
                System.out.println(todos.get(num - 1));
            } else if (fstWord.equals("unmark")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                Task task = todos.get(num - 1);
                task.unmarkAsDone();
                System.out.println("No worries! I'll unmark that for you: ");
                System.out.println(todos.get(num - 1));
            } else if (fstWord.equals("todo")) {
                System.out.println("Gotcha bro! I've added this task:");
                Task todo = new ToDo(input.substring(5));
                System.out.println(todo);
                todos.add(todo);
                System.out.printf("Now you have %d tasks in your list%n", todos.size());
            } else {
                String substring = input.substring(input.indexOf('/') + 4);
                if (fstWord.equals("deadline")) {
                    System.out.println("Gotcha bro! I've added this task:");
                    Task deadline = new Deadline(input.substring(9, input.indexOf('/')),
                            substring);
                    System.out.println(deadline);
                    todos.add(deadline);
                    System.out.printf("Now you have %d tasks in your list%n", todos.size());
                } else if (fstWord.equals("event")) {
                    System.out.println("Gotcha bro! I've added this task:");
                    Task event = new Event(input.substring(6, input.indexOf('/')),
                            substring);
                    System.out.println(event);
                    todos.add(event);
                    System.out.printf("Now you have %d tasks in your list%n", todos.size());
                } else {
                    todos.add(new Task(input));
                    System.out.println("added: " + input);
                }
            }
        }
    }
}
