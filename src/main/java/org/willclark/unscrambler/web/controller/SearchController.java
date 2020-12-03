package org.willclark.unscrambler.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.willclark.unscrambler.jpa.entity.WordEntity;
import org.willclark.unscrambler.jpa.repository.WordRepository;
import org.willclark.unscrambler.web.dto.SearchResults;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class SearchController {
    
    @Autowired
    private WordRepository wordRepository;

    @RequestMapping(path = "/search/{letters}", method = RequestMethod.GET)
    public ResponseEntity<?> search(@PathVariable("letters") final String letters) {
        log.info("got a search request for `{}`", letters);
        List<WordEntity> results = wordRepository.findWordsContainingLetters(letters);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new SearchResults(results));
    }
    
}