package org.willclark.unscrambler.jpa.repository;

import java.util.List;

import org.willclark.unscrambler.jpa.entity.WordEntity;

public interface WordRepositorySearch {
    List<WordEntity> findWordsContainingLetters(String letters);
}