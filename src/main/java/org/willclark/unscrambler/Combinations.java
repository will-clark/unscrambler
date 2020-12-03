package org.willclark.unscrambler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.willclark.unscrambler.utils.WordUtils;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Combinations {

    private static final int MIN_SIZE = 2;

    @Getter private final Letters input;
    @Getter private Set<String> combinations;

    public Combinations(@NonNull String input) {
        this(new Letters(input));
    }

    public Combinations(@NonNull Letters input) {
        this.input = input;
        generate();
    }

    private void generate() {
        Timer timer = Timer.start();
        combinations = new TreeSet<>();
        for (int r = MIN_SIZE; r <= input.getChars().length; r++) {
            addAll(_generate(r));
        }
        timer.stop();
        log.debug(timer.duration());
    }

    private List<String> _generate(int size) { 
        return _generate(new char[size], 0, input.getChars().length-1, 0, size, new ArrayList<>(0));
    }

    private List<String> _generate(char[] data, int start, int end, int index, int size, List<String> combinations) { 
        // Current combination is ready to be printed, print it 
        if (index == size) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j < size; j++) {
                sb.append(data[j]);
            }
            add(sb.toString());
        }
        else {
  
            // replace index with all possible elements. The condition 
            // "end-i+1 >= size-index" makes sure that including one element 
            // at index will make a combination with remaining elements 
            // at remaining positions 
            for (int i=start; i<=end && end-i+1 >= size-index; i++) { 
                data[index] = input.getChars()[i]; 
                _generate(data, i+1, end, index+1, size, combinations);
            }

        }

        return combinations;
    }

    private void addAll(List<String> combinations) {
        for (String each : combinations) {
            add(each);
        }
    }

    private void add(@NonNull String combination) {
        this.combinations.add(WordUtils.computeIndexChars(combination));
    }

    public static void main(String...args) {
        Combinations combinations = new Combinations("million");
        System.out.println(combinations.getCombinations());
    }

}