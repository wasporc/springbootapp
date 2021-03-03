package ru.geekbrain.bean;

import org.springframework.stereotype.Component;
import ru.geekbrain.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart  implements Store{

    private List<Product> productList;

    public Cart() {
        this.productList = new ArrayList<>();
    }

    @Override
    public void showAllProduct() {
        if (this.productList.size() != 0)
            this.productList.forEach(product -> System.out.println(product.toString()));
        else System.out.println("Сия пуста");
    }

    @Override
    public Product getProductById(int id) {
        return this.productList
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        this.productList.add(product);
    }

    @Override
    public void removeProduct(int id) {
        this.productList.removeIf(product -> product.getId() == id);
    }
}
