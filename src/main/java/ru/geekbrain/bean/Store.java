package ru.geekbrain.bean;

import ru.geekbrain.entity.Product;

public interface Store {
    void showAllProduct();
    Product getProductById(int id);
    void addProduct(Product product);
    void removeProduct(int id);
}
