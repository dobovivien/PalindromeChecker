package com.example.palindrome.service;

import com.example.palindrome.comparator.PalindromeLengthComparator;
import com.example.palindrome.converter.WordDataConverter;
import com.example.palindrome.dto.InputDto;
import com.example.palindrome.dto.WordDataDto;
import com.example.palindrome.model.WordData;
import com.example.palindrome.repository.WordDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WordDataService {

    private static final PalindromeLengthComparator PALINDROME_LENGTH_COMPARATOR = new PalindromeLengthComparator();

    @Autowired
    private WordDataRepository wordDataRepository;

    @Autowired
    private WordDataConverter wordDataConverter;

    public Long addWord(InputDto dto) {

        WordData wordData = wordDataConverter.inputDtoToEntity(dto);

        wordData.setLongestPalindromeSize(getPalindromeLength(dto.getContent()));

        WordData savedWord = wordDataRepository.save(wordData);

        return savedWord.getId();
    }

    public int getPalindromeLength(String word) {

        List<String> substrings = getSubstrings(word);

        Optional<Integer> longestPalindrome = substrings.stream()
                .map(substring -> {
                    substring = substring.replaceAll("[^a-zA-Z]", "");
                    substring = substring.toLowerCase();

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.insert(0, substring);

                    if (stringBuilder.reverse().toString().equals(substring)) {
                        return substring.length();
                    } else {
                        return 0;
                    }
                })
                .max(Comparator.naturalOrder());

        return longestPalindrome.orElse(0);
    }

    public List<String> getSubstrings(String word) {

        List<String> substrings = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            for (int j = i + 1; j <= word.length(); j++) {
                substrings.add(word.substring(i, j));
            }
        }

        return substrings.stream()
                .filter(substring -> substring.length() >= 2)
                .collect(Collectors.toList());
    }

    public List<WordDataDto> getAll() {

        return wordDataRepository.findAll().stream()
                .map(word -> wordDataConverter.entityToDto(word))
                .sorted(PALINDROME_LENGTH_COMPARATOR)
                .collect(Collectors.toList());
    }
}
