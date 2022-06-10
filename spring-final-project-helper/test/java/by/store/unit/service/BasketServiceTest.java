package by.store.unit.service;

import by.store.form.ProductForm;
import by.store.logs.EventLogger;
import by.store.model.Product;
import by.store.repository.ProductDAO;
import by.store.service.BasketServiceImpl;
import by.store.service.UserService;
import by.store.еxceptions.QuantityWrongInputException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private EventLogger eventLogger;

    @InjectMocks
    private BasketServiceImpl basketService;


    private static final long code = 7;

    private static final String test = "test";

    private Product product = new Product(test, 1, 100);
    private Product productTest = new Product(test, 1, 9);
    private ProductForm productForm = new ProductForm(productTest);

    @Before
    public void setup() {
        basketService.cleanBasket();
        product.setCode(code);
        productTest.setCode(code);
        productForm.setCode(code);
        when(productDAO.findById(code).get()).thenReturn(product);
        basketService.addBasket(code, 9);
        basketService.addBasket(code, 9);
    }

    @Test
    public void testAddBasket() {
        List<ProductForm> productForms = new ArrayList<>();
        productForms.add(productForm);
        productForms.add(productForm);
        assertArrayEquals(productForms.toArray(), basketService.getBasket().toArray());
    }

    @Test(expected = QuantityWrongInputException.class)
    public void testAddBasketFail() {
        basketService.addBasket(code, -9);
        basketService.addBasket(code, null);
    }

    @Test
    public void testCleanBasket() {
        basketService.cleanBasket();
        assertEquals(0, basketService.getBasket().size());
    }

    @Test
    public void deleteProductBasket() {
        basketService.deleteProductBasket(code);
        assertEquals(0, basketService.getBasket().size());
    }
}
