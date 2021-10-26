package pl.danel.gymex.domain.asserts;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -39974261532767577L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public NotFoundException(String arg0) {
        super(arg0);
    }

    public NotFoundException(Throwable arg0) {
        super(arg0);
    }

}
