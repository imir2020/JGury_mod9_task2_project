package by.service;

import by.database.repository.ProductRepository;
import by.dto.product_dto.FromProductDtoToBase;
import by.dto.product_dto.ProductDto;
import by.mapper.classes.products.DtoToProduct;
import by.mapper.classes.products.ProductToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productsRepository;
    private final ProductToDto productToDto;
    private final DtoToProduct dtoToProduct;

    public List<ProductDto> findAll() {
        log.info("ProductService: Attempt to extract productDto collection in method findAll()");
        return productsRepository.findAll()
                .stream()
                .map(productToDto::mapFrom)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(Long id) {
        log.info("ProductService: Attempt to extract productDto object in method findById()");
        return productsRepository.findById(id).map(productToDto::mapFrom);
    }

    @Transactional
    public ProductDto save(FromProductDtoToBase fromProductDtoToBase) {
        log.info("ProductService: Attempt to save fromProductDtoToBase object in method save()");
        return Optional.of(fromProductDtoToBase)
                .map(dtoToProduct::mapFrom)
                .map(productsRepository::save)
                .map(productToDto::mapFrom)
                .orElseThrow();
    }

    @Transactional
    public void updateProductPriceForOneById(Long priceForOne, Long id) {
        productsRepository.updateProductPriceForOneById(priceForOne, id);
        log.info("ProductService: Attempt to update price for one product " +
                 "by id for object Product in method updateProductPriceForOneById()");
    }

    @Transactional
    public Optional<ProductDto> update(Long id, FromProductDtoToBase fromProductDtoToBase) {

        log.info("ProductService: Attempt to update in method update()");
        return productsRepository.findById(id)
                .map(product -> dtoToProduct.mapFrom(fromProductDtoToBase))
                .map(product -> {
                    product.setId(id);
                    return product;
                })
                .map(productsRepository::saveAndFlush)
                .map(productToDto::mapFrom);
    }

    public boolean delete(Long id) {
        log.info("ProductService: Attempt to delete Product object by your id, in method delete()");
        return productsRepository.findById(id)
                .map(product -> {
                    productsRepository.deleteById(product.getId());
                    productsRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
