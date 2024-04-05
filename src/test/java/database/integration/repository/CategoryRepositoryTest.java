package database.integration.repository;

import annotation.IT;
import by.database.entity.Category;
import by.database.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@RequiredArgsConstructor
public class CategoryRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryTest.class);
    private final CategoryRepository categoryRepository;

    @Test
    public void findAll() {
        List<Category> categories = categoryRepository.findAllBy();
        assertTrue(categories.size()>0);
        categories.forEach(System.out::println);
        logger.info("Result list from method findAll(): {}", categories);
    }


    @Test
    public void findById() {
        Long id = 2L;
        Category category = categoryRepository.findById(id).get();
        System.out.println(category + " method FindById()");
        logger.info("this is category example {}", category);
    }

    @Test
    public void save() {
        Category category = Category.builder()
                .categoryName("Натуральные соки")
                .build();
        categoryRepository.save(category);
        logger.info("Object was saved in method save(): {}", category);

    }

    @Test
    public void update() {
        Long id = 4L;
        String nextName = "Unknown_111";
        categoryRepository.updateName(nextName, id);
        Category category = categoryRepository.findById(id).get();

        assertEquals(nextName,category.getCategoryName());
    }


    @Test
    public void delete() {
        Long id = 4L;
        categoryRepository.deleteById(id);

        var expected = categoryRepository.findById(id);
        assertTrue(expected.isEmpty());
    }
}
