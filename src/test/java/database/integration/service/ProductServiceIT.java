package database.integration.service;


import annotation.IT;
import by.database.entity.Category;
import by.database.entity.Supplier;
import by.database.repository.CategoryRepository;
import by.database.repository.ProductRepository;
import by.database.repository.SupplierRepository;
import by.dto.product_dto.FromProductDtoToBase;
import by.dto.product_dto.ProductDto;
import by.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class ProductServiceIT {
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Test
    public void findAll() {
        var resultList = productService.findAll();
        assertTrue(resultList.size() > 0);
        resultList.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        var actual = productService.findById(id);
        assertTrue(actual.isPresent());
    }

    @Test
    public void save() {
        var category = categoryRepository.findById(3L).get();
        var supplier = supplierRepository.findById(3L).get();
        FromProductDtoToBase productDto = FromProductDtoToBase.builder()
                .name("Мясо")
                .supplierId(1L)
                .count(125L)
                .priceForOne(500L)
                .categoryId(1L)
                .build();
        productService.save(productDto);
    }

    @Test
    public void updateProductPriceForOneById() {
        Long id = 3L;
        Long priceForOne = 600L;
        productService.updateProductPriceForOneById(priceForOne, id);

      var product =  productService.findById(id);
        assertTrue(product.isPresent());
        assertEquals(priceForOne,product.get().getPriceForOne());
    }

    @Test
    public void delete() {
//        Long id = 1L;
//        productService.delete(id);
//        var actual = productService.findById(id);
//        assertTrue(actual.isEmpty());
    }
}
