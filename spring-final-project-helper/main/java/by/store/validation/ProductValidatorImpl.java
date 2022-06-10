package by.store.validation;

import by.store.form.ProductForm;
import org.springframework.stereotype.Component;

@Component
public class ProductValidatorImpl implements ProductValidator {

    @Override
    public boolean checkProduct(ProductForm product) {
        boolean flag = (product.getName() != null && product.getName().length() > 0
                && product.getPrice() != null && product.getPrice() > 0
                && product.getQuantity() != null && product.getQuantity() > 0
                && product.getFile() != null);
        return flag;
    }

}
