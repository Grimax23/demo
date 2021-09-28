package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.demo.ValidationUtils.*;

@Repository
public class InMemoryStringRepository {
    private Set<String> storageSet = ConcurrentHashMap.newKeySet();

    public List<String> save(List<String> newStrings) {
        List<String> addedStrings = new ArrayList<>(newStrings);

        for (String str : newStrings) {
            if (isValidPattern(str) && !storageSet.contains(str))
                storageSet.add(str);
            else addedStrings.remove(str);
        }
        return addedStrings;
    }

    public List<String> findByStringPattern(String pattern) {
        if (!isValidPattern(pattern)) throw new RuntimeException("Pattern is invalid");
        List<String> list = new ArrayList<>();
        for (String str : storageSet) {
            if (str.contains(pattern)) {
                list.add(str);
            }
        }
        return list;
    }

    public Integer size() {
        return storageSet.size();
    }

    public List<String> getAll() {
        return storageSet.stream().toList();
    }
}
