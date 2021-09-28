package com.example.demo;

import java.util.List;

public class ValidationUtils {

    public static boolean isValidContent(List<String> newStrings) {
        return  newStrings.stream()
                .map(s -> s.replaceAll("\\s+", ""))
                .noneMatch(String::isEmpty);
    }
    public static boolean isValidPattern(String str) {
        return  !str.replaceAll("\\s+", "").isEmpty();
    }

}
