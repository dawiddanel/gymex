package pl.danel.gymex.domain.asserts;

public class InvalidArgumentException extends Exception {

    private static final long serialVersionUID = -39974261548767577L;

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public InvalidArgumentException(String arg0) {
        super(arg0);
    }

    public InvalidArgumentException(Throwable arg0) {
        super(arg0);
    }

}
