package database.integration.service;


import annotation.IT;
import by.dto.category_dto.FromDtoToCategory;
import by.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class CategoryServiceIT {
    private final CategoryService categoryService;

    @Test
    public void findAll() {
        var actualResult = categoryService.findAll();
        assertTrue(actualResult.size() > 0);
        actualResult.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        var actual = categoryService.findById(id);
        System.out.println(actual);
    }

    @Test
    public void updateName() {
//        Long id = 3L;
//        String categoryName = "Unknown_111";
//        categoryService.update(id,categoryName);
//
//        var category = categoryService.findById(id);
//        assertTrue(category.isPresent());
//        assertEquals(categoryName,category.get().getCategoryName());
    }

    @Test
    public void save() {
        FromDtoToCategory category = FromDtoToCategory.builder()
                .categoryName("Натуральные соки")
                .build();
        categoryService.save(category);
    }

    @Test
    public void deleteById() {
//        Long id = 1L;
//        categoryService.delete(id);
//
//       var expected =  categoryService.findById(id);
//       assertTrue(expected.isEmpty());


    }
}
