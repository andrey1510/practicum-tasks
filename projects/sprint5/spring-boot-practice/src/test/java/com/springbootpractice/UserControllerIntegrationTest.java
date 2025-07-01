package com.springbootpractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUsers() throws Exception {
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"))
            .andExpect(xpath("//table").exists())
            .andExpect(xpath("//table//tr").nodeCount(4))
            .andExpect(xpath("//table//tr[1]/td").string("Дмитрий"))
            .andExpect(xpath("//table//tr[2]/td").string("Полина"))
            .andExpect(xpath("//table//tr[3]/td").string("Иван"))
            .andExpect(xpath("//table//tr[4]/td").string("Анна"))
            .andExpect(view().name("users/listOfAll"))
            .andExpect(model().attributeExists("users"))
            .andExpect(model().attribute("users",    // другой вариант
                org.hamcrest.Matchers.contains("Дмитрий", "Полина", "Иван", "Анна")))
            .andExpect(xpath("//table//tr").nodeCount(4));
    }
}
