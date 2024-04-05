package database.integration.controller;


import annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class LoginControllerIT {
    private final MockMvc mockMvc;

    @Test
    void loginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login/login"));

    }

    @Test
    void logout() throws Exception {
        mockMvc.perform(get("/logout", 3))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login/login"));
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(post("/login")
                        .param("password", "111"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/employees/*"));
    }
}
