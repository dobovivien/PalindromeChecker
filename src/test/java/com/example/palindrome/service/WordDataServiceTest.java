package com.example.palindrome.service;

import com.example.palindrome.converter.WordDataConverter;
import com.example.palindrome.dto.InputDto;
import com.example.palindrome.dto.WordDataDto;
import com.example.palindrome.model.WordData;
import com.example.palindrome.repository.WordDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class WordDataServiceTest {

    private static final Long ID = 1L;
    private static final WordData WORD_DATA_PALINDROME = new WordData(ID, "mom", LocalDateTime.now(), 3);
    private static final WordData WORD_DATA_NOT_PALINDROME = new WordData(ID, "cat", LocalDateTime.now(), 0);
    private static final WordDataDto WORD_DATA_DTO_PALINDROME = new WordDataDto("mom", LocalDateTime.now(), 3);
    private static final WordDataDto WORD_DATA_DTO_NOT_PALINDROME = new WordDataDto("cat", LocalDateTime.now(), 0);
    private static final InputDto INPUT_DTO_PALINDROME = new InputDto("mom", LocalDateTime.now());
    
    @MockBean
    private WordDataConverter wordDataConverter;

    @MockBean
    private WordDataRepository wordDataRepository;

    @Autowired
    private WordDataService wordDataService;

    @Test
    void addWord_ok() {
        when(wordDataConverter.inputDtoToEntity(INPUT_DTO_PALINDROME)).thenReturn(WORD_DATA_PALINDROME);
        when(wordDataRepository.save(WORD_DATA_PALINDROME)).thenReturn(WORD_DATA_PALINDROME);

        wordDataService.addWord(INPUT_DTO_PALINDROME);

        verify(wordDataConverter).inputDtoToEntity(INPUT_DTO_PALINDROME);
        verify(wordDataRepository).save(WORD_DATA_PALINDROME);
    }

    @Test
    void getPalindromeLength_is_palindrome() {

        Assertions.assertEquals(3, wordDataService.getPalindromeLength(WORD_DATA_PALINDROME.getContent()));
    }

    @Test
    void getPalindromeLength_is_not_palindrome() {

        Assertions.assertEquals(0, wordDataService.getPalindromeLength(WORD_DATA_NOT_PALINDROME.getContent()));
    }

    @Test
    void getSubstrings_contains_3_substrings() {
        List<String> substingsOfMom = new ArrayList<>();
        substingsOfMom.add("mo");
        substingsOfMom.add("mom");
        substingsOfMom.add("om");

        Assertions.assertEquals(3, wordDataService.getSubstrings(WORD_DATA_PALINDROME.getContent()).size());
        Assertions.assertEquals(substingsOfMom, wordDataService.getSubstrings(WORD_DATA_PALINDROME.getContent()));
    }

    @Test
    void getAll() {
        List<WordData> wordDataDtoList = new ArrayList<>();
        wordDataDtoList.add(WORD_DATA_PALINDROME);
        wordDataDtoList.add(WORD_DATA_NOT_PALINDROME);

        when(wordDataConverter.entityToDto(WORD_DATA_PALINDROME)).thenReturn(WORD_DATA_DTO_PALINDROME);
        when(wordDataConverter.entityToDto(WORD_DATA_NOT_PALINDROME)).thenReturn(WORD_DATA_DTO_NOT_PALINDROME);
        when(wordDataRepository.findAll()).thenReturn(wordDataDtoList);

        wordDataService.getAll();

        verify(wordDataConverter).entityToDto(WORD_DATA_PALINDROME);
        verify(wordDataConverter).entityToDto(WORD_DATA_NOT_PALINDROME);
        verify(wordDataRepository).findAll();
    }
}
