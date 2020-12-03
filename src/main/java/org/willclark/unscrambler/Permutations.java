package org.willclark.unscrambler;

import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Permutations {

    @Getter private final Letters input;
    @Getter private Set<String> permuations;
    
    public Permutations(@NonNull String input) {
        this(new Letters(input));
    }

    public Permutations(@NonNull Letters input) {
        this.input = input;
        generate();
    }

    private void generate() {        
        permuations = new TreeSet<>();
        loop(input.getChars());
    }
 
	private void loop(char[] array) {
		loop(array, array.length);
	}
 
	private void loop(char[] array, int n) {
		int[] c = new int[n];
		add(array);
		boolean plus = false;
		for (int i = 0; i < n; ) {
			if (c[i] < i) {
				if (i % 2 == 0) {
					swap(array, 0, i);
				} else {
					swap(array, c[i], i);
				}
				add(array);
				plus = !plus;
				c[i]++;
				i = 0;
			} else {
				c[i] = 0;
				i++;
			}
		}
    }

    private void swap(char[] array, int a, int b) {
		char o = array[a];
		array[a] = array[b];
		array[b] = o;
    }
    
    private void add(char[] array) {
        permuations.add(array.toString());
    }
        
    public static void main(String... args) {
        Timer timer = Timer.start();
        new Permutations("baseballfoot");
        timer.stop();
        log.debug(timer.duration());
    }

}