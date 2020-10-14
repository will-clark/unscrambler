package org.willclark.unscrambler.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.willclark.unscrambler.jpa.entity.Word;

public class ConvertTextFileToCSV {
    
    public static void main(String... args) throws Exception {
        new ConvertTextFileToCSV().run();
    }

    public void run() throws Exception {
        try (FileWriter fw = new FileWriter("./src/main/resources/words.csv"); PrintWriter pw = new PrintWriter(fw)) {
            Path path = Paths.get(ConvertTextFileToCSV.class.getClassLoader().getResource("words.txt").toURI());            
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(value -> {
                    Word word = new Word(value);
                    pw.printf(String.format("%s,%s,%d%n", word.getValue(), word.getChars(), word.getLength()));
                });
            }
        }
    }

}
