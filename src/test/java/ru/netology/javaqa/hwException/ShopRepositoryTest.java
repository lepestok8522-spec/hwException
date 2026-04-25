package ru.netology.javaqa.hwException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {
    @Test
    public void shouldRemoveExistingProduct() {

        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Телефон", 20000);
        Product product2 = new Product(2, "Ноутбук", 50000);
        Product product3 = new Product(3, "Планшет", 15000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.removeById(2);

        Product[] expected = {product1, product3};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExistentProduct() {
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Телефон", 20000);
        Product product2 = new Product(2, "Ноутбук", 50000);

        repository.add(product1);
        repository.add(product2);

        assertThrows(NotFoundException.class, () -> {
            repository.removeById(999); // Несуществующий ID
        });
    }

    @Test
    public void shouldThrowNotFoundExceptionWithCorrectMessage() {

        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Телефон", 20000);
        repository.add(product1);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            repository.removeById(100);
        });
        assertEquals("Element with id: 100 not found", exception.getMessage());
    }

    @Test
    public void shouldRemoveFirstProduct() {

        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Телефон", 20000);
        Product product2 = new Product(2, "Ноутбук", 50000);
        Product product3 = new Product(3, "Планшет", 15000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.removeById(1);

        Product[] expected = {product2, product3};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveLastProduct() {

        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Телефон", 20000);
        Product product2 = new Product(2, "Ноутбук", 50000);
        Product product3 = new Product(3, "Планшет", 15000);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);


        repository.removeById(3);


        Product[] expected = {product1, product2};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }
}

