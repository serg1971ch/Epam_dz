package by.store.integration;

import by.store.config.AppConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class ProductControllerTest {

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
        Assert.assertNotNull(wac.getBean("productController"));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void testProducts() throws Exception {
        this.mvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("products"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testEditProductPage() throws Exception {
        final int code = 5;
        this.mvc.perform(get("/admin/edit/{code}", code))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testEditProduct() throws Exception {
        final int code = 5;
        MockMultipartFile file = new MockMultipartFile("coffee", "coffee.jpg", MediaType.IMAGE_JPEG_VALUE, "coffee.jpg".getBytes());
        mvc.perform(MockMvcRequestBuilders.multipart("/admin/edit/product/{code}", code)
                .file(file)
                .param("name", "test")
                .param("price", "1")
                .param("quantity", "9"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
//        final int code = 5;
//        this.mvc.perform(post("/admin/edit/product/{code}", code)
//                .param("name", "test")
//                .param("price", "1")
//                .param("quantity", "9")
//                .param("file", "coffee.jpg"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/products"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testDeleteProduct() throws Exception {
        final int code = 1;
        this.mvc.perform(post("/admin/delete/{code}", code))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void testAddProductPage() throws Exception {
        this.mvc.perform(get("/admin/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addProduct"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void createProduct() throws Exception {
        this.mvc.perform(post("/admin/add/product")
                .param("name", "test")
                .param("price", "1")
                .param("quantity", "9")
                .param("file", ""))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }

}
