package org.willclark.unscrambler.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.willclark.unscrambler.jpa.entity.WordEntity;

@RepositoryRestResource(collectionResourceRel = "words", path = "words")
public interface WordRepository extends PagingAndSortingRepository<WordEntity, Long>, WordRepositorySearch {
    
}