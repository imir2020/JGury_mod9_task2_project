package database.integration.controller;


import annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserRegistrationControllerIT {
    private final MockMvc mockMvc;

    @Test
    void userRegistrationPage() throws Exception {
        mockMvc.perform(get("/users/user"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("user", "statusName"))
                .andExpect(view().name("user/user_registration"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/users/{id}", 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("user", "userStatus"))
                .andExpect(view().name("user/user"));
    }

    @Test
    void createUser() throws Exception {
//        mockMvc.perform(post("/users/registration")
//                        .param("name", "Anton")
//                        .param("birthDay", "1978-02-12")
//                        .param("password", "111")
//                        .param("status", "ADMIN"))
//                .andExpectAll(
//                        status().is3xxRedirection(),
//                        redirectedUrlPattern("/users/{\\d+}"));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(post("/users/6/delete")
                .param("password","999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login/login"));
    }
}
