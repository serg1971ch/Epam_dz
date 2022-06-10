package by.store.unit.service;


import by.store.logs.EventLogger;
import by.store.model.User;
import by.store.repository.UserDAO;
import by.store.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private EventLogger eventLogger;

    @InjectMocks
    private UserServiceImpl userService;

    private static final String test = "test";

    private User user = new User(test, test);

    @Test
    public void testFindByUsername() {
        when(userDAO.findByUsername(test)).thenReturn(user);
        User user = userDAO.findByUsername(test);
        assertNotNull(user);
        assertEquals(test, user.getUsername());
        verify(userDAO, times(1)).findByUsername(test);
    }

    @Test
    public void testFindByUsernameFail() {
        when(userDAO.findByUsername(test)).thenReturn(null);
        User user = userDAO.findByUsername(test);
        assertNull(user);
        verify(userDAO, times(1)).findByUsername(test);
    }

    @Test
    public void testSave() {
        when(userDAO.findByUsername(test)).thenReturn(null);
        boolean flag = userService.save(user);
        assertTrue(flag);
        verify(userDAO, times(1)).findByUsername(test);
        verify(userDAO, times(1)).save(user);
    }

    @Test
    public void testSaveFail() {
        when(userDAO.findByUsername(test)).thenReturn(user);
        boolean flag = userService.save(user);
        assertFalse(flag);
        verify(userDAO, times(1)).findByUsername(test);
        verify(userDAO, times(0)).save(user);
    }

}
