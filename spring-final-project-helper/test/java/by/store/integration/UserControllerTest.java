package by.store.integration;

import by.store.Constants;
import by.store.config.AppConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Random;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesUserController() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("userController"));
    }

    @Test
    public void testAccessDenied() throws Exception {
        this.mvc.perform(get("/error"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testHome() throws Exception {
        this.mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

        this.mvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testRegistrationPage() throws Exception {
        this.mvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void testRegistrationUser() throws Exception {
        Random random = new Random();
        String test = "test" + random.nextInt();
        this.mvc.perform(post("/appRegistration")
                .param(Constants.USERNAME, test)
                .param(Constants.PASSWORD, test))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.MESSAGE, Constants.YOU_ARE_REGISTERED))
                .andExpect(view().name("login"));
    }

    @Test
    public void testRegistrationUserFail() throws Exception {
        String test = "test";
        this.mvc.perform(post("/appRegistration")
                .param(Constants.USERNAME, test)
                .param(Constants.PASSWORD, test))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.MESSAGE, Constants.USER_EXISTS))
                .andExpect(view().name("registration"));
    }


    @Test
    public void login() throws Exception {
        this.mvc.perform(post("/appLogin")
                .param(Constants.USERNAME, "admin")
                .param(Constants.PASSWORD, "admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    public void logout() throws Exception {
        mvc.perform(get("/appLogout"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}
