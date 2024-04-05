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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ProductControllerIT {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("product/products"))
                .andExpect(model().attribute("products", hasSize(5)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/products/{id}", 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("product/one_product"));
    }

    @Test
    void registration()throws Exception{
//        mockMvc.perform(get("/products/registration")
//                        .param("name", "tails")
//                        .param("count", "1234")
//                        .param("priceForOne", "12"))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(view().name("products/product_save"))
//                .andExpect(model().attributeExists("product"));

    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/products")
                        .param("name", "tails")
                        .param("count", "1234")
                        .param("priceForOne", "12"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/products/*"));
    }

    @Test
    void update() throws Exception {
//        mockMvc.perform(post("/products/3/update")
//                        .param("name", "tails")
//                        .param("count", "1234")
//                        .param("priceForOne", "12"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/products/3"));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/products/6/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }
}
