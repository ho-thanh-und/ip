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
            String[] words = input.split(" ");
            String fstWord = words[0]; // Access the first word (safe since every input has at least one word)

            if (input.equals("thank you")) {
                System.out.println("You're welcome, kind individual!");
                break;
            } else if (input.equals("list")) {
                if (todos.isEmpty()) {
                    System.out.println("Your task list is empty.");
                } else {
                    int i = 1;
                    for (Task todo : todos) {
                        System.out.printf("%d. %s%n", i++, todo);
                    }
                }
            } else if (fstWord.equals("mark")) {
                try {
                    if (words.length < 2) {
                        throw new IllegalArgumentException("What task number would you like me to mark?");
                    }
                    int num = Integer.parseInt(words[1]);
                    if (num < 1 || num > todos.size()) {
                        throw new IllegalArgumentException("Oops! I don't see that task");
                    }
                    Task task = todos.get(num - 1);
                    task.markAsDone();
                    System.out.println("Congrats! Another task down: ");
                    System.out.println(task);
                } catch (NumberFormatException e) {
                    System.out.println("Oops! It should be an integer!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (fstWord.equals("unmark")) {
                try {
                    if (words.length < 2) {
                        throw new IllegalArgumentException("What task number would you like me to unmark?");
                    }
                    int num = Integer.parseInt(words[1]);
                    if (num < 1 || num > todos.size()) {
                        throw new IllegalArgumentException("Oops! It should be an integer!");
                    }
                    Task task = todos.get(num - 1);
                    task.unmarkAsDone();
                    System.out.println("No worries! I'll unmark that for you: ");
                    System.out.println(task);
                } catch (NumberFormatException e) {
                    System.out.println("Oops! It should be an integer!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (fstWord.equals("todo")) {
                try {
                    if (input.trim().length() <= 5) {
                        throw new IllegalArgumentException("Oh dear! The todo seems to be empty");
                    }
                    Task todo = new ToDo(input.substring(5).trim());
                    todos.add(todo);
                    System.out.println("Gotcha bro! I've added this task:");
                    System.out.println(todo);
                    System.out.printf("Now you have %d tasks in your list%n", todos.size());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (fstWord.equals("deadline")) {
                try {
                    if (!input.contains("/by")) {
                        throw new IllegalArgumentException("A deadline must include '/by' followed by the due date");
                    }
                    String description = input.substring(9, input.indexOf('/')).trim();
                    String by = input.substring(input.indexOf('/') + 4).trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new IllegalArgumentException("The deadline description or due date can't be empty bro");
                    }
                    Task deadline = new Deadline(description, by);
                    todos.add(deadline);
                    System.out.println("Gotcha bro! I've added this task:");
                    System.out.println(deadline);
                    System.out.printf("Now you have %d tasks in your list%n", todos.size());
                } catch (IllegalArgumentException e) {
                    System.out.println("Uh Oh! " + e.getMessage());
                }
            } else if (fstWord.equals("event")) {
                try {
                    if (!input.contains("/at")) {
                        throw new IllegalArgumentException("An event must include '/at' followed by the event time");
                    }
                    String description = input.substring(6, input.indexOf('/')).trim();
                    String at = input.substring(input.indexOf('/') + 4).trim();
                    if (description.isEmpty() || at.isEmpty()) {
                        throw new IllegalArgumentException("The event description or time can't be empty bro");
                    }
                    Task event = new Event(description, at);
                    todos.add(event);
                    System.out.println("Gotcha bro! I've added this task:");
                    System.out.println(event);
                    System.out.printf("Now you have %d tasks in your list%n", todos.size());
                } catch (IllegalArgumentException e) {
                    System.out.println("Uh Oh! " + e.getMessage());
                }
            } else {
                todos.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }
}

