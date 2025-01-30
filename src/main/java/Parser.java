/**
 * Deals with user commands
 */

public class Parser {
    public String[] parse(String s, int lim) {
        return s.split(" ", lim);
    }
}
