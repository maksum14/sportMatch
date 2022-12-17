package sportradar.football.exceptions;

public class NoSuchMatchException extends RuntimeException {
    public NoSuchMatchException(String msg) {
        super(msg);
    }
}
