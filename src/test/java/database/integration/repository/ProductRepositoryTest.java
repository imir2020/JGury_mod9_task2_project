package database.integration.repository;

import annotation.IT;
import by.database.entity.Category;
import by.database.entity.Product;
import by.database.entity.Supplier;
import by.database.repository.CategoryRepository;
import by.database.repository.ProductRepository;
import by.database.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class ProductRepositoryTest {
    private final Logger log = LoggerFactory.getLogger("ProductsDaoTest");

    private final ProductRepository productsRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository suppliersRepository;

    @Test
    public void findAll() {
        List<Product> products = productsRepository.findAllBy();
        assertTrue(products.size() > 0);
        assertThat(products).hasSize(5);

        log.info("This is list of products from method findAll(): {}", products);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Optional<Product> product = productsRepository.findById(id);
        assertTrue(product.isPresent());

        log.info("Object from method findBuId(): {}", product.get());
    }

    @Test
    public void save() {
        Product product = Product.builder()
                .name("Мясо")
                .count(125L)
                .priceForOne(500L)
                .build();
        Long categoryId = 3L;
        Long supplierId = 3L;
        Category category = categoryRepository.findById(categoryId).get();
        Supplier supplier = suppliersRepository.findById(supplierId).get();
        product.setSupplier(supplier);
        product.setCategory(category);

        productsRepository.save(product);
        log.info("Object was saved in method save(): {}", product);
    }

    @Test
    public void update() {
        Long id = 1L;
        Long priceForOne = 250L;
        productsRepository.updateProductPriceForOneById(priceForOne, id);
        Product product = productsRepository.findById(id).get();
        assertEquals(priceForOne, product.getPriceForOne());
        log.info("Object was updated in method update(): {}", product);
    }

    @Test
    public void delete() {
        Long id = 4L;

        productsRepository.deleteById(id);
        Optional<Product> product = productsRepository.findById(id);
        assertTrue(product.isEmpty());
        log.warn("Object was deleted in method delete():{}", product);
    }
}
