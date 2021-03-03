package ru.geekbrain.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.geekbrain.bean.Store;
import ru.geekbrain.entity.Product;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private Store productRepository;

    @Autowired
    private Store cart;

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean loopFlag = true;
            printInfo();
            while (loopFlag) {
                System.out.println("(1-5) : ");
                int command = -1;
                if (scanner.hasNextInt()) {
                    command = scanner.nextInt();
                } else {
                    System.out.println("Not found Int value :"
                            + scanner.next());
                    continue;
                }
                switch (command) {
                    case 1:
                        productRepository.showAllProduct();
                        break;
                    case 2:
                        cart.showAllProduct();
                        break;
                    case 3: {
                        System.out.println("Добавление в корзину");
                        int id = getProductId(scanner);
                        if (id == -1) {
                            System.out.println("Неверный номер");
                            break;
                        }
                        Product productById = productRepository.getProductById(id);
                        if (productById != null) cart.addProduct(productById);
                        break;
                    }
                    case 4:
                        System.out.println("Вытащить из корзины");
                        int id = getProductId(scanner);
                        if (id == -1) {
                            System.out.println("Неверный номер");
                            break;
                        }
                        cart.removeProduct(id);
                        break;
                    case 5:
                        System.out.println("Exit command");
                        loopFlag = false;
                        break;
                }
            }
        }
        System.out.println("stop app");
    }

    private void printInfo() {
        System.out.println("1. Список продуктов на складе\n" +
                "2. Список продуктов в корзине\n" +
                "3. Добавить продукт в корзину\n" +
                "4. Вытащить из корзины\n" +
                "5. Выход\n");
    }

    private int getProductId(Scanner scanner) {
        boolean loopBreaker = true;
        int number = -1;
        while (loopBreaker) {
            System.out.println("Введите номер : ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                loopBreaker = false;
            } else {
                String str = scanner.next();
                if ("exit".equals(str)) {
                    loopBreaker = false;
                    continue;
                }
                System.out.println("Not found Int value (exit for cancel):"
                        + str);
            }
        }
        return number;
    }
}
