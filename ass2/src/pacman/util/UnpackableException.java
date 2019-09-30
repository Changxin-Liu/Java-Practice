package pacman.util;

/**
 * Exception throw when a game file connot be loaded.
 */
public class UnpackableException extends Exception {


    /**
     * Standard UnpackableException which takes no parameters, like Exception()
     */
    public UnpackableException() {
        super();
    }

    /**
     * An UnpackableException that contains a message.
     *
     * @param message the message to show
     */
    public UnpackableException(String message) {
        super(message);
    }
}
