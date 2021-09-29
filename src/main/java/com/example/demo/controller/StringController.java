package com.example.demo.controller;

import com.example.demo.repository.InMemoryStringRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.ValidationUtils.*;

@RestController
public class StringController {

    private final InMemoryStringRepositoryImpl repository;

    public StringController(InMemoryStringRepositoryImpl repository) {
        this.repository = repository;
    }

    @PostMapping("addStrings")
    public ResponseEntity<List<String>> addStrings(@RequestBody List<String> newStrings) {
        if (!isValidContent(newStrings))
            return new ResponseEntity("Request contains invalid content", HttpStatus.BAD_REQUEST);
        List<String> savedStrings = repository.save(newStrings);
        if (savedStrings == null) {
            return new ResponseEntity(newStrings, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(savedStrings, HttpStatus.CREATED);
        }
    }


    @GetMapping("/substrings")
    public ResponseEntity<List<String>> getByStringPattern(@RequestParam(name = "str") String str) {
        if (!isValidPattern(str)) return new ResponseEntity("Parameter str is invalid", HttpStatus.BAD_REQUEST);
        List<String> foundStrings;
        try {
            foundStrings  = repository.findByStringPattern(str);
        }catch (IllegalStateException ex){
            return new ResponseEntity("Parameter str is invalid", HttpStatus.BAD_REQUEST);
        }
        if (foundStrings.isEmpty()) {
            return new ResponseEntity(str, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(foundStrings, HttpStatus.OK);
        }
    }

    @GetMapping("/count")
    public ResponseEntity getStringsCount() {
        return new ResponseEntity(repository.count(), HttpStatus.OK);
    }

    @GetMapping("/strings")
    public ResponseEntity<List<String>> getAll() {
        return new ResponseEntity(repository.getAll(), HttpStatus.OK);
    }

}