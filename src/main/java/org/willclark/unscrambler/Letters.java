package org.willclark.unscrambler;

import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Letters {
    
    private static final int MAX_SIZE = 15;

    @Getter private final String input;
    @Getter private final char[] chars;
    @Getter(lazy = true) private final Set<String> combinations = combinations();

    public Letters(@NonNull String input) {
        String _input = input.trim().toLowerCase();
        if (_input.isEmpty()) throw new IllegalArgumentException("input may not be empty");
        if (_input.length() > MAX_SIZE) throw new IllegalArgumentException("input may not exceed " + MAX_SIZE + " characters");
        if (!_input.matches("[a-z]+")) throw new IllegalArgumentException("input may only be alphabet characters");

        this.input = _input;
        this.chars = this.input.toCharArray();
    }

    public int length() {
        return input.length();
    }

    private Set<String> combinations() {
        Set<String> combinations = new TreeSet<>();
        combinations.addAll(new Combinations(input).getCombinations());
        log.debug("{} combinations", combinations.size());
        return combinations;
    }

    public static void main(String... args) {
        Timer timer = Timer.start();
        log.debug(new Letters("baseball").getCombinations());
        timer.stop();
        log.info(timer.duration());
    }

}