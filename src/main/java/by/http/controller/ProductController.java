package by.http.controller;


import by.dto.product_dto.FromProductDtoToBase;
import by.mapper.classes.categories.DtoToCategory;
import by.mapper.classes.suppliers.DtoToSupplier;
import by.service.CategoryService;
import by.service.ProductService;
import by.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final DtoToCategory dtoToCategory;
    private final DtoToSupplier dtoToSupplier;

    @GetMapping
    public String findAll(Model model) {
        var result = productService.findAll();
        model.addAttribute("products", result);
        result.forEach(System.out::println);
        return "product/products";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        log.info("Load models of categoryService.findAll() & supplierService.findAll()");
        return productService.findById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    model.addAttribute("categories", categoryService.findAll());
                    model.addAttribute("suppliers", supplierService.findAll());
                    return "product/one_product";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registrProduct(Model model, FromProductDtoToBase fromProductDtoToBase) {
        model.addAttribute("product", fromProductDtoToBase);
        return "product/product_save";
    }


    @PostMapping
    public String create(FromProductDtoToBase productDtoToBase) {
        var result = productService.save(productDtoToBase);
        System.out.println(result.getId() + " from create Method");
        return "redirect:/products/" + result.getId();
    }


    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, FromProductDtoToBase fromProductDtoToBase) {
        log.info("ProductController: Id from path: /{id}/update, from method update, is: {}", id);
               return productService.update(id, fromProductDtoToBase)
                .map(path -> "redirect:/products/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!productService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/products";
    }
}
