package by.http.restcontroller;


import by.dto.product_dto.FromProductDtoToBase;
import by.dto.product_dto.ProductDto;
import by.mapper.classes.categories.DtoToCategory;
import by.mapper.classes.suppliers.DtoToSupplier;
import by.service.CategoryService;
import by.service.ProductService;
import by.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final DtoToCategory dtoToCategory;
    private final DtoToSupplier dtoToSupplier;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        log.info("Attempt finding object from findById method()");
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> create(@Validated @RequestBody FromProductDtoToBase productDtoToBase) {
        var result = productService.save(productDtoToBase);
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable("id") Long id,
                                             @Validated @RequestBody FromProductDtoToBase fromProductDtoToBase) {
        log.info("ProductController: Id from path: /{id}, from method update, is: {}", id);
        return productService.update(id, fromProductDtoToBase)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (!productService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
