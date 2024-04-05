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
public class SupplierControllerIT {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/suppliers"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("supplier/suppliers"))
                .andExpect(model().attribute("suppliers", hasSize(5)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/suppliers/{id}", 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("supplier"))
                .andExpect(view().name("supplier/one_supplier"));
    }

    @Test
    void registration()throws Exception{
        mockMvc.perform(get("/suppliers/registration")
                        .param("name","Horns")
                        .param("address","NovoYralsk")
                        .param("email","r@yk.com")
                        .param("phoneNumber","7(923)444-44-44"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("supplier/supplier_save"))
                .andExpect(model().attributeExists("supplier"));

    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/suppliers")
                        .param("name","Horns")
                        .param("address","NovoYralsk")
                        .param("email","r@yk.com")
                        .param("phoneNumber","7(923)444-44-44"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/suppliers/*"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/suppliers/3/update")
                        .param("name","Horns")
                        .param("address","NovoYralsk")
                        .param("email","r@yk.com")
                        .param("phoneNumber","7(923)444-44-44"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/suppliers/{\\d+}"));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/suppliers/5/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/suppliers"));
    }
}
