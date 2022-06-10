package by.store.service;

import by.store.logs.EventLogger;
import by.store.model.User;
import by.store.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private EventLogger eventLogger;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, EventLogger eventLogger) {
        this.userDAO = userDAO;
        this.eventLogger = eventLogger;
    }

    @Override
    public boolean save(User user) {
        boolean flag = false;
        if (user.getUsername() != null && user.getUsername().length() > 0 &&
                user.getPassword() != null && user.getPassword().length() > 0 &&
                userDAO.findByUsername(user.getUsername()) == null) {
            userDAO.save(user);
            flag = true;
        }
        return flag;
    }


    @Override
    public String usernameNow() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}