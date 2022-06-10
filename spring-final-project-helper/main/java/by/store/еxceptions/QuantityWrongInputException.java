package by.store.еxceptions;

import by.store.Constants;

public class QuantityWrongInputException extends WrongInputException {

    public QuantityWrongInputException() {
        this(Constants.WRONG_INPUT_QUANTITY);
    }

    public QuantityWrongInputException(String message) {
        super(message);
    }
}
