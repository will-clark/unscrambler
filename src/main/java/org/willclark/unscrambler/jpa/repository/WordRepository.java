package org.willclark.unscrambler.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.willclark.unscrambler.jpa.entity.Word;

@RepositoryRestResource
public interface WordRepository extends CrudRepository<Word, Long> {
    
}
