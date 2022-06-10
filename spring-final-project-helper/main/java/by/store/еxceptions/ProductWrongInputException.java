package by.store.еxceptions;

import by.store.Constants;

public class ProductWrongInputException extends WrongInputException {

    public ProductWrongInputException() {
        this(Constants.WRONG_INPUT_PRODUCT);
    }

    public ProductWrongInputException(String message) {
        super(message);
    }
}
