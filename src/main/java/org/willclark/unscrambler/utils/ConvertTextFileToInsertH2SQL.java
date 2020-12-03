package org.willclark.unscrambler.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.willclark.unscrambler.Word;

public class ConvertTextFileToInsertH2SQL {
    
    private static final String INSERT_SQL = "INSERT INTO word (value, chars, length, created, modified) VALUES ('%s', '%s', %d, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());%n";

    private int i;

    public static void main(String... args) throws Exception {
        new ConvertTextFileToInsertH2SQL().run();
    }

    public void run() throws Exception {
        try (FileWriter fw = new FileWriter("./src/main/resources/data-h2.sql"); PrintWriter pw = new PrintWriter(fw)) {

            Path path = Paths.get(ConvertTextFileToInsertMSSQL.class.getClassLoader().getResource("words.txt").toURI());            
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(value -> {

                    Word word = new Word(value);
                    pw.printf(INSERT_SQL, word.getValue(), word.getChars(), word.getLength());
                    i++;

                });
            }

        }
    }

}
