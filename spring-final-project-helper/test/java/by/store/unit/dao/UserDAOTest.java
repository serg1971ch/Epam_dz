package by.store.unit.dao;

import by.store.config.AppConfig;
import by.store.model.User;
import by.store.repository.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    private static final String test = "test";
    private User user = new User(test, test);

    @Before
    public void setup() {
        if (userDAO.findByUsername(test) == null) {
            userDAO.save(user);
        }
    }

    @Test
    public void testFindByUsername() {
        User user = userDAO.findByUsername(test);
        assertEquals(test, user.getUsername());
    }

    @Test(expected = Exception.class)
    public void testSave() {
        userDAO.save(user);
    }

}
