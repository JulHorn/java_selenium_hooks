package hooks.exception;

/**
 * This runtime exception will be thrown, if something concerning a hook goes wrong, e.g. failed to a hook.
 **/
public class HookException extends RuntimeException {

    /**
     * Serial number.
     **/
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     **/
    public HookException(String message) {
        super(message);
    }

    /**
     * Constructor.
     **/
    public HookException(String message, Throwable thrown) {
        super(message, thrown);
    }
}
