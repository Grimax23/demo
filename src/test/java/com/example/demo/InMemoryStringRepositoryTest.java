package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InMemoryStringRepositoryTest {
    @InjectMocks
    private InMemoryStringRepository repository;

    @Test
    void save() {
        List<String> addedStrings = Arrays.asList("Str1", "Str2", "Str3");
        List<String> savedStrings = repository.save(addedStrings);
        assertEquals(addedStrings, savedStrings);
    }

    @Test
    void saveWithDublicates() {
        List<String> addedStrings = Arrays.asList("Str1", "Str1", "Str1");
        List<String> savedStrings = repository.save(addedStrings);
        assertEquals(1, savedStrings.size());
    }

    @Test
    void saveWithInvalidContent() {
        List<String> addedStrings = Arrays.asList("Str1", "", " ");
        List<String> savedStrings = repository.save(addedStrings);
        assertEquals(1, savedStrings.size());
    }

    @Test
    void findByStringPattern() {
        repository.save(Arrays.asList("Str1", "Str2", "Str3"));
        List<String> findedStrings = repository.findByStringPattern("St");
        assertEquals(3, findedStrings.size());
    }

    @Test
    void findByInvalidStringPattern() {
        repository.save(Arrays.asList("Str1", ""));
        assertThrows(RuntimeException.class, ()->repository.findByStringPattern(""));
    }

    @Test
    void size() {
        repository.save(Arrays.asList("Str1", "Str2", "Str3"));
        assertEquals(3,repository.size());
    }

    @Test
    void getAll() {
        repository.save(Arrays.asList("Str1", "Str2", "Str3"));
        assertEquals(3,repository.size());
    }
}