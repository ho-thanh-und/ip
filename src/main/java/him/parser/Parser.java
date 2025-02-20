package him.parser;


/**
 * Deals with user commands
 */
public class Parser {
    public String[] parse(String input, int limit) {
        return input.split(" ", limit);
    }

    /**
     * Parses the command type from the given input.
     * @param input The raw user input string.
     * @return The recognized CommandType (BYE, DONE, UNDONE, etc.),
     *         or UNKNOWN if no match is found.
     */
    public CommandType parseCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return CommandType.UNKNOWN;
        }

        String trimmed = input.trim().toLowerCase();

        if (trimmed.equals("bye")) {
            return CommandType.BYE;
        } else if (trimmed.startsWith("done")) {
            return CommandType.DONE;
        } else if (trimmed.startsWith("undone")) {
            return CommandType.UNDONE;
        } else if (trimmed.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (trimmed.equals("list")) {
            return CommandType.LIST;
        } else if (trimmed.startsWith("find")) {
            return CommandType.FIND;
        } else if (trimmed.startsWith("todo")
                || trimmed.startsWith("deadline")
                || trimmed.startsWith("event")
                || trimmed.startsWith("dowithin")) {
            return CommandType.ADD_TASK;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}
