package nl.eveoh.mytimetable.apiclient.exception;

/**
 * @author Erik van Paassen
 */
public class NoUsableMyTimetableApiUrlException extends RuntimeException {

    public NoUsableMyTimetableApiUrlException() {
    }

    public NoUsableMyTimetableApiUrlException(String message) {
        super(message);
    }

    public NoUsableMyTimetableApiUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUsableMyTimetableApiUrlException(Throwable cause) {
        super(cause);
    }

    public NoUsableMyTimetableApiUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
