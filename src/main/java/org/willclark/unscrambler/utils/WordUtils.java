package org.willclark.unscrambler.utils;

import com.google.common.collect.TreeMultiset;

import lombok.NonNull;

public class WordUtils {
    
    public static String computeIndexChars(@NonNull String word) {
        TreeMultiset<Character> set = TreeMultiset.create();
        for (char each : word.toCharArray()) set.add(each);
        
        StringBuilder sb = new StringBuilder();
        for (char each : set) sb.append(each);
        return sb.toString();
    }

}