package by.http.restcontroller;

import by.database.entity.Category;
import by.dto.category_dto.CategoryDto;
import by.dto.category_dto.FromDtoToCategory;
import by.dto.pages.PageResponse;
import by.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping("/page")
    public ResponseEntity<PageResponse<CategoryDto>> findAll(Pageable pageable){
        Page<CategoryDto> page = categoryService.findAll(pageable);
        return ResponseEntity.ok(PageResponse.of(page));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("The request was directed in 'categories' page");
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> save(@Validated @RequestBody FromDtoToCategory category) {
        System.out.println(category.toString());
        var result = categoryService.save(category);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Long id,@Validated @RequestBody FromDtoToCategory category) {
        return categoryService.update(id,category)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (!categoryService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
