package by.store.integration;

import by.store.Constants;
import by.store.config.AppConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class BasketControllerTest {

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
        Assert.assertNotNull(wac.getBean("basketController"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void testAddBasket() throws Exception {
        final int code = 5;
        this.mvc.perform(post("/add/basket/{code}", code)
                .param("count", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void testBasket() throws Exception {
        this.mvc.perform(get("/basket"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("basket"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void testBuyProducts() throws Exception {
        this.mvc.perform(post("/buy"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute(Constants.MESSAGE, Constants.YOU_BOUGHT_THESE_PRODUCTS))
                .andExpect(view().name("basket"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void deleteProductBasket() throws Exception {
        final int code = 5;
        this.mvc.perform(post("/delete/{code}", code))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/basket"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void testCleanBasket() throws Exception {
        this.mvc.perform(post("/clean"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/basket"));
    }

}
