package database.integration.controller;

import annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class CategoryControllerIT {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("category/categories"))
                .andExpect(model().attribute("categories", hasSize(6)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/categories/{id}", 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("category/category"));

    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/categories")
                        .param("categoryName", "Pryanik"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/categories/*"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/categories/3/update")
                        .param("categoryName", "Конфеты"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/categories/3"));

    }

    @Test
    void delete() throws Exception {
//        mockMvc.perform(post("/categories/1/delete"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/categories"));
    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/categories/registration")
                .param("categoryName", "Osmiym"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("category/new_category_save"))
                .andExpect(model().attributeExists("category"));
    }
}
