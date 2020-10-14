package org.willclark.unscrambler.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "WORD")
@ToString
@EqualsAndHashCode
public class Word implements BaseEntity, Serializable {
    
    private static final long serialVersionUID = 1L;

    public Word(@NonNull String value) {
        setValue(value);
    }

    protected Word() {}

    @Id
    @Getter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String guid;
    
    @Getter
    @Setter
    private boolean deleted;

    @Getter
    private LocalDateTime created;

    @Getter
    private LocalDateTime modified;

    @Getter
    private String value;

    public void setValue(@NonNull String value) {
        this.value = value;
        this.chars = computeChars();
        this.length = value.length();
    }

    @Getter
    private String chars;

    private String computeChars() {
        TreeSet<Character> set = new TreeSet<>();
        for (char each : value.toCharArray()) set.add(each);
        
        StringBuilder sb = new StringBuilder();
        for (char each : set) sb.append(each);
        return sb.toString();
    }

    @Getter
    private int length;

}