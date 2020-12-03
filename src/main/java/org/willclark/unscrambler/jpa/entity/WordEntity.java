package org.willclark.unscrambler.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.willclark.unscrambler.Word;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table(name = "word")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WordEntity extends BaseEntity {

    public WordEntity(@NonNull Word word) {
        this.value = word.getValue();
        this.chars = word.getChars();
        this.length = word.getLength();
    }

    protected WordEntity() {}

    @Getter
    private String value;

    @Getter
    private String chars;

    @Getter
    private int length;

}