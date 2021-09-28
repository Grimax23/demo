package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void isValidContentPositiveTest() {
        List<String> content = Arrays.asList("Str1", "str2", "tr3");
        assertTrue(ValidationUtils.isValidContent(content));
    }

    @Test
    void isValidContentNegativeTest() {
        List<String> content = Arrays.asList("Str1", "str2", "tr3", "");
        assertFalse(ValidationUtils.isValidContent(content));
    }

    @Test
    void isValidPatternPositiveTest() {
        assertTrue(ValidationUtils.isValidPattern("s"));
    }

    @Test
    void isValidPatternNegativeTest() {
        assertFalse(ValidationUtils.isValidPattern(""));
        assertFalse(ValidationUtils.isValidPattern(" "));
    }
}