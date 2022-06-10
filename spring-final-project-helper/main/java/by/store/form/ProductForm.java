package by.store.form;

import by.store.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


public class ProductForm {

    private Long code;

    private String name;

    private Integer price;

    private Integer quantity;

    private MultipartFile file;


    public ProductForm() {
    }

    public ProductForm(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public Product getProduct() {
        Product product = new Product(this.name, this.price, this.quantity);
        product.setCode(this.code);
        try {
            byte[] image = this.file.getBytes();
            if (image != null && image.length > 0) {
                product.setImage(this.file.getBytes());
            }
        } catch (Exception e) {
        }
        return product;
    }

    public ProductForm(String name, Integer price, Integer quantity, MultipartFile file) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.file = file;
    }

    public ProductForm(Long code, String name, Integer price, Integer quantity, MultipartFile file) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.file = file;
    }

    public ProductForm(Long code, String name, Integer price, Integer quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product {");
        sb.append("code = ").append(code);
        sb.append(", name = ").append(name);
        sb.append(", price = ").append(price);
        sb.append(", quantity = ").append(quantity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductForm that = (ProductForm) o;
        return code == that.code &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
