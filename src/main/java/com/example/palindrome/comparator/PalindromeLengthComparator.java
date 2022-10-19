package com.example.palindrome.comparator;

import com.example.palindrome.dto.WordDataDto;

import java.util.Comparator;

public class PalindromeLengthComparator implements Comparator<WordDataDto> {

    @Override
    public int compare(WordDataDto word1, WordDataDto word2) {
        return word2.getLongestPalindromeSize() - word1.getLongestPalindromeSize();
    }
}
