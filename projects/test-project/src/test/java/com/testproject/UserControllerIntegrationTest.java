package com.testproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(UserController.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testCreateProduct() throws Exception {
        Model model = mock(Model.class);

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"))
            .andExpect(view().name("users/listOfAll"))
            .andExpect(model().attributeExists("users"))
            .andExpect(model().attribute("users", List.of("Дмитрий", "Полина", "Иван", "Анна"))); // Проверяем содержимое атрибута

        verify(model, times(1)).addAttribute(eq("users"), eq(List.of("Дмитрий", "Полина", "Иван", "Анна")));

    }
}
