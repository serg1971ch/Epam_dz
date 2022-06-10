package by.store.service;

import by.store.Constants;
import by.store.repository.ProductDAO;
import by.store.form.ProductForm;
import by.store.logs.EventLogger;
import by.store.model.Product;
import by.store.validation.ProductValidator;
import by.store.еxceptions.ProductWrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private UserService userService;

    private ProductDAO productDAO;

    private EventLogger eventLogger;

    private ProductValidator productValidator;

    @Autowired
    public ProductServiceImpl(UserService userService, ProductDAO productDAO, EventLogger eventLogger, ProductValidator productValidator) {
        this.userService = userService;
        this.productDAO = productDAO;
        this.eventLogger = eventLogger;
        this.productValidator = productValidator;
    }

    @Override
    public Product findByCode(Long code) {
        return productDAO.findById(code).get();
    }

    @Override
    public void save(ProductForm product) {
        if (productValidator.checkProduct(product)) {
            productDAO.save(product.getProduct());
            eventLogger.logEvent(userService.usernameNow() + Constants.SAVE + product);
        } else {
            throw new ProductWrongInputException();
        }
    }

    @Override
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @Override
    public void delete(Long code) {
        productDAO.deleteById(code);
        eventLogger.logEvent(userService.usernameNow() + Constants.DELETE + code);
    }

    @Override
    public void update(ProductForm product) {
        if (productValidator.checkProduct(product)) {
            productDAO.save(product.getProduct());
            eventLogger.logEvent(userService.usernameNow() + Constants.UPDATE + product);
        } else {
            throw new ProductWrongInputException();
        }
    }

}
