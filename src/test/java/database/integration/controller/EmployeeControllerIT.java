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
public class EmployeeControllerIT {
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("employee/employees"))
                .andExpect(model().attribute("employees", hasSize(10)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/employees/{id}", 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("employee/employee"));
    }

    @Test
    void registration() throws Exception {
        mockMvc.perform(get("/employees/registration")
                        .param("lastName", "Ivanov")
                        .param("middleName", "Ivanovich")
                        .param("name", "Ivan")
                        .param("dateBirth", "1992-12-12")
                        .param("phoneNumber", "8(234)456-34-78")
                        .param("address", "Vladivostok"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("employee/employee_save"))
                .andExpect(model().attributeExists("employee", "ranks"));

    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/employees")
                        .param("lastName", "Ivanov")
                        .param("middleName", "Ivanovich")
                        .param("name", "Ivan")
                        .param("dateBirth", "1992-12-12")
                        .param("phoneNumber", "8(234)456-34-78")
                        .param("address", "Vladivostok"))
                //.param("rank","1"))//Как отобразить объект  параметр?
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/employees/*"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/employees/3/update")
                        .param("lastName", "Ivanov")
                        .param("middleName", "Ivanovich")
                        .param("name", "Ivan")
                        .param("dateBirth", "1992-12-12")
                        .param("phoneNumber", "8(234)456-34-78")
                        .param("address", "Vladivostok"))
                //.param("rank","1"))//Как отобразить объект  параметр?
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees/3"));

    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/employees/8/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));
    }
}
