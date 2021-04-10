package by.meearlyam.tictactoe.exception;

public class InputParsingException extends Exception{
    /**
     * Constructor with specified string
     *
     * @param message string
     */
    public InputParsingException(String message) {
        super(message);
    }

    /**
     * Constructor with specified string and exception
     *
     * @param message string
     * @param e       error covered
     */
    public InputParsingException(String message, Throwable e) {
        super(message, e);
    }
}
