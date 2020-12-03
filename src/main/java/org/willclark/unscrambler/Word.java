package org.willclark.unscrambler;

import java.io.Serializable;

import org.willclark.unscrambler.utils.WordUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Word implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public Word(@NonNull String value) {
        setValue(value);
    }

    protected Word() {}
    
    @Getter
    private String value;

    public void setValue(@NonNull String value) {
        this.value = value;
        this.chars = WordUtils.computeIndexChars(value);
        this.length = value.length();
    }

    @Getter
    private String chars;

    @Getter
    private int length;
    
}