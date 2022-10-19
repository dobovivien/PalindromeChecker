package com.example.palindrome.controller;

import com.example.palindrome.dto.InputDto;
import com.example.palindrome.dto.WordDataDto;
import com.example.palindrome.service.WordDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/palindrome-checker")
public class WordDataController {

    @Autowired
    private WordDataService wordDataService;

    @PostMapping
    public ResponseEntity<Long> addWord(@Valid @RequestBody InputDto dto) {
        return new ResponseEntity<>(wordDataService.addWord(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WordDataDto>> getAll() {
        return new ResponseEntity<>(wordDataService.getAll(), HttpStatus.OK);
    }
}
