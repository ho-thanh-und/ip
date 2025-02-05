package him.parser;


/**
 * Deals with user commands
 */
public class Parser {
    public String[] parse(String input, int limit) {
        return input.split(" ", limit);
    }
}
