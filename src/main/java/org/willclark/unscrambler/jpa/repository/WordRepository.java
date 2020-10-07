package org.willclark.unscrambler.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.willclark.unscrambler.jpa.entity.WordEntity;

public interface WordRepository extends CrudRepository<WordEntity, Long> {
    
}
