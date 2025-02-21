package com.testproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testFindAllByPrice() {
        Product product1 = new Product();
        product1.setName("Продукт 1");
        product1.setPrice(100L);

        Product product2 = new Product();
        product2.setName("Продукт 2");
        product2.setPrice(200L);

        Product product3 = new Product();
        product3.setName("Продукт 3");
        product3.setPrice(100L);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productsWithPrice100 = productRepository.findAllByPrice(100L);

        assertThat(productsWithPrice100).hasSize(2);
        assertThat(productsWithPrice100).extracting(Product::getName)
            .containsExactlyInAnyOrder("Продукт 1", "Продукт 3");

        List<Product> productsWithPrice200 = productRepository.findAllByPrice(200L);
        assertThat(productsWithPrice200).hasSize(1);
        assertThat(productsWithPrice200).extracting(Product::getName)
            .containsExactly("Продукт 2");
    }
}
