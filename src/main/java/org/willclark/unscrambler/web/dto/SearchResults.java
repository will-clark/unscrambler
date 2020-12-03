package org.willclark.unscrambler.web.dto;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.willclark.unscrambler.jpa.entity.WordEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class SearchResults {

    private int count;
    private Set<String> oneLetterWords = new TreeSet<>();
    private Set<String> twoLetterWords = new TreeSet<>();
    private Set<String> threeLetterWords = new TreeSet<>();
    private Set<String> fourLetterWords = new TreeSet<>();
    private Set<String> fiveLetterWords = new TreeSet<>();
    private Set<String> sixLetterWords = new TreeSet<>();
    private Set<String> sevenLetterWords = new TreeSet<>();
    private Set<String> eightLetterWords = new TreeSet<>();
    private Set<String> nineLetterWords = new TreeSet<>();
    private Set<String> tenLetterWords = new TreeSet<>();
    private Set<String> elevenLetterWords = new TreeSet<>();
    private Set<String> twelveLetterWords = new TreeSet<>();
    private Set<String> thirteenLetterWords = new TreeSet<>();
    private Set<String> fourteenLetterWords = new TreeSet<>();
    private Set<String> fifteenLetterWords = new TreeSet<>();

    public SearchResults(@NonNull List<WordEntity> results) {
        for (WordEntity word : results) {
            if (word.getLength() == 1) {
                oneLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 2) {
                twoLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 3) {
                threeLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 4) {
                fourLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 5) {
                fiveLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 6) {
                sixLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 7) {
                sevenLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 8) {
                eightLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 9) {
                nineLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 10) {
                tenLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 11) {
                elevenLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 12) {
                twelveLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 13) {
                thirteenLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 14) {
                fourteenLetterWords.add(word.getValue());
                count++;
            }
            else if (word.getLength() == 15) {
                fifteenLetterWords.add(word.getValue());
                count++;
            }                                                            
        }
    }

}
