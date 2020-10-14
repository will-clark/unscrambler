package org.willclark.unscrambler.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.willclark.unscrambler.jpa.entity.Word;

public class ConvertTextFileToInsertSQL {
    
    private static final String ENABLE_TXN = "SET IMPLICIT_TRANSACTIONS ON%n";
    private static final String BEGIN_TXN  = "BEGIN TRANSACTION%n";
    private static final String INSERT_SQL = "INSERT INTO word (value, chars, length) VALUES ('%s', '%s', %d);%n";
    private static final String COMMIT_TXN = "COMMIT TRANSACTION%n";

    private int i;

    public static void main(String... args) throws Exception {
        new ConvertTextFileToInsertSQL().run();
    }

    public void run() throws Exception {
        try (FileWriter fw = new FileWriter("./src/main/resources/words.sql"); PrintWriter pw = new PrintWriter(fw)) {
            pw.printf(ENABLE_TXN);

            Path path = Paths.get(ConvertTextFileToInsertSQL.class.getClassLoader().getResource("words.txt").toURI());            
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(value -> {
                    if (i % 100 == 0) pw.printf(BEGIN_TXN);
                    Word word = new Word(value);
                    pw.printf(INSERT_SQL, word.getValue(), word.getChars(), word.getLength());
                    i++;
                    if (i % 100 == 0) pw.printf(COMMIT_TXN);
                });
            }

            pw.printf(COMMIT_TXN);
        }
    }

}
