package com.example.demo.repository;

import java.util.List;

public interface InMemoryStringRepository {

    List<String> save(List<String> newStrings);

    List<String> findByStringPattern(String pattern);

    long count();

    List<String> getAll();

}
