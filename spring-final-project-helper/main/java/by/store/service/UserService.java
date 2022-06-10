package by.store.service;

import by.store.model.User;

public interface UserService {

    boolean save(User user);

    String usernameNow();

}
