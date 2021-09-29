package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InMemoryStringRepository {

    List<String> save(List<String> newStrings);

    List<String> findByStringPattern(String pattern);

    long count();

    List<String> getAll();

}
