package by.store.еxceptions;

import by.store.Constants;

public class WrongInputException extends RuntimeException {

    public WrongInputException() {
        this(Constants.WRONG_INPUT);
    }

    public WrongInputException(String message) {
        super(message);
    }
}
