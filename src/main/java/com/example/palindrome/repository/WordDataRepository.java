package com.example.palindrome.repository;

import com.example.palindrome.model.WordData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordDataRepository extends JpaRepository<WordData, Long> {
}
