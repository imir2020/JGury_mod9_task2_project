package by.http.controller;

import by.dto.category_dto.CategoryDto;
import by.dto.category_dto.FromDtoToCategory;
import by.dto.pages.PageResponse;
import by.dto.product_dto.ProductDto;
import by.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/page")
    public String findAll(Model model, Pageable pageable){
        Page<CategoryDto> page = categoryService.findAll(pageable);
        System.out.println(page + " page from CategoryController");
        model.addAttribute("categories", PageResponse.of(page));
        System.out.println(model.getAttribute("categories") + " pageResponse from CategoryController");
        return "category/categories";
    }

    @GetMapping
    public String findAll(Model model) {
        var categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        log.info("The request was directed in 'categories' page");
        return "category/categories";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        var result = categoryService.findById(id);
        model.addAttribute("category", result.get());
        return "category/category";
//        return categoryService.findById(id)
//                .map(category ->{
//                    model.addAttribute("category",category);
//                            return "category/category";
//                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registrCategory(Model model, @ModelAttribute("category") FromDtoToCategory fromDtoToCategory) {
        model.addAttribute("category", fromDtoToCategory);
        return "category/new_category_save";
    }

    @PostMapping
    public String save(Model model, FromDtoToCategory category) {
        var dto = categoryService.save(category);
        model.addAttribute("category", category);
        return "redirect:/categories/" + dto.getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute FromDtoToCategory category) {
        categoryService.update(id, category);
        return "redirect:/categories/{id}";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!categoryService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/categories";
    }
}
