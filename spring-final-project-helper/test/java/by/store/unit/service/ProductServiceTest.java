package by.store.unit.service;


import by.store.form.ProductForm;
import by.store.logs.EventLogger;
import by.store.model.Product;
import by.store.repository.ProductDAO;
import by.store.service.ProductServiceImpl;
import by.store.service.UserService;
import by.store.validation.ProductValidator;
import by.store.еxceptions.ProductWrongInputException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private EventLogger eventLogger;

    @Mock
    private ProductValidator productValidator;

    @InjectMocks
    private ProductServiceImpl productService;

    private static final long code = 7;
    private static final String test = "test";
    private ProductForm productForm = new ProductForm((long) 7, test, 1, 9);
    private Product product = new Product(test, 1, 9);

    @Test
    public void testFindByCode() {
        when(productDAO.findById(code).get()).thenReturn(product);
        Product product = productService.findByCode(code);
        assertNotNull(product);
        assertEquals(test, product.getName());
        verify(productDAO, times(1)).findById(code).get();
    }

    @Test
    public void testFindByCodeFail() {
        when(productDAO.findById(code).get()).thenReturn(null);
        Product product = productService.findByCode(code);
        assertNull(product);
        verify(productDAO, times(1)).findById(code).get();
    }

    @Test
    public void testSave() {
        when(productValidator.checkProduct(productForm)).thenReturn(true);
        productService.save(productForm);
        verify(productValidator, times(1)).checkProduct(productForm);
    }

    @Test(expected = ProductWrongInputException.class)
    public void testSaveFail() {
        when(productValidator.checkProduct(any())).thenReturn(false);
        productService.save(productForm);
        verify(productValidator, times(1)).checkProduct(productForm);
    }

    @Test
    public void testGetAll() {
        when(productDAO.findAll()).thenReturn(new ArrayList<>());
        List<Product> products = productService.getAll();
        assertNotNull(products);
        verify(productDAO, times(1)).findAll();
    }

    @Test
    public void testGetAllFail() {
        when(productDAO.findAll()).thenReturn(null);
        List<Product> products = productService.getAll();
        assertNull(products);
        verify(productDAO, times(1)).findAll();
    }

//    @Test
//    public void testDelete() {
//        when(productDAO.delete(code)).thenReturn(product);
//        Product product = productService.delete(code);
//        assertNotNull(product);
//        verify(productDAO, times(1)).delete(code);
//    }
//
//    @Test
//    public void testDeleteFail() {
//        when(productDAO.delete(code)).thenReturn(null);
//        Product product = productService.delete(code);
//        assertNull(product);
//        verify(productDAO, times(1)).delete(code);
//    }

    @Test
    public void testUpdate() {
        when(productValidator.checkProduct(any())).thenReturn(true);
        productService.update(productForm);
        verify(productValidator, times(1)).checkProduct(any());
    }

    @Test(expected = ProductWrongInputException.class)
    public void testUpdateFail() {
        when(productValidator.checkProduct(any())).thenReturn(false);
        productService.update(productForm);
        verify(productValidator, times(1)).checkProduct(any());
    }
}