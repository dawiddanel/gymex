package pl.danel.gymex.asserts;

public class InvalidStateException extends RuntimeException {

    private static final long serialVersionUID = -39974261548767577L;

    public InvalidStateException() {
        super();
    }

    public InvalidStateException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public InvalidStateException(String arg0) {
        super(arg0);
    }

    public InvalidStateException(Throwable arg0) {
        super(arg0);
    }

}
