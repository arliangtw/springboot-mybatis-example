package com.example.myapp;
import com.example.myapp.controller.MyController;
import com.example.myapp.model.UsersDto;
import com.example.myapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllModels() throws Exception {
        UsersDto userDto = new UsersDto();
        userDto.setId(1L);
        userDto.setName("John Doe");

        List<UsersDto> expectedModels = Collections.singletonList(userDto);

        Mockito.when(userService.getUserById(1)).thenReturn(expectedModels);

        mockMvc.perform(MockMvcRequestBuilders.get("/models"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"));
    }
}