package ru.geekbrain.bean;

import org.springframework.stereotype.Component;
import ru.geekbrain.entity.Product;

import java.util.Comparator;
import java.util.List;

@Component
public class ProductRepository implements Store{
    private List<Product> productList;

    public ProductRepository() {
        this.productList = List.of(
                new Product(1, "apple", 2.0),
                new Product(2, "banana", 3.0),
                new Product(3, "orange", 4.0),
                new Product(4, "lemon", 5.0),
                new Product(5, "potato", 6.0));
    }

    @Override
    public Product getProductById(int id){
        return this.productList
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        int id = this.productList
                .stream()
                .max(Comparator.comparingInt(Product::getId))
                .orElse(new Product(1, null, 0)).getId();
        product.setId(id);
        this.productList.add(product);
    }

    @Override
    public void removeProduct(int id) {
        this.productList.removeIf(product -> product.getId() == id);
    }

    @Override
    public void showAllProduct(){
        this.productList.forEach(product -> System.out.println(product.toString()));
    }


    @Override
    public String toString() {
        return "ProductRepository{" +
                "productList=" + productList +
                '}';
    }
}
