package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StringController.class)
class StringControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InMemoryStringRepository repository;

    @Test
    void addStrings() throws Exception {
        List<String> addedStrings = Arrays.asList("Str1", "str2", "tr3");
        String bodyValue = om.writeValueAsString(addedStrings);
        when(repository.save(addedStrings)).thenReturn(addedStrings);

        mvc.perform(post("/addStrings").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                        .content(bodyValue))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addStringsWithInvalidContent() throws Exception {
        List<String> addedStrings = Arrays.asList("str1","");
        String bodyValue = om.writeValueAsString(addedStrings);
        when(repository.save(addedStrings)).thenReturn(addedStrings);

        mvc.perform(post("/addStrings").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                        .content(bodyValue))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByStringPattern() throws Exception {
        List<String> addedStrings = Arrays.asList("Str1", "str2", "tr3");
        when(repository.findByStringPattern("tr")).thenReturn(addedStrings);

        mvc.perform(get("/substrings?str=tr"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getByInvalidStringPattern() throws Exception {
        List<String> addedStrings = Arrays.asList("Str1", "str2", "tr3");
        when(repository.findByStringPattern("")).thenReturn(addedStrings);

        mvc.perform(get("/substrings?str="))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getStringsCount() throws Exception {
        when(repository.size()).thenReturn(0);

        mvc.perform(get("/stringscount"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}