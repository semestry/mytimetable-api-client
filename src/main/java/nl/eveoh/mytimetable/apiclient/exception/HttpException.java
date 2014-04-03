package nl.eveoh.mytimetable.apiclient.exception;

/**
 * @author Erik van Paassen
 */
public class HttpException extends RuntimeException {

    public HttpException() {}

    public HttpException(Throwable e) {
        super(e);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
