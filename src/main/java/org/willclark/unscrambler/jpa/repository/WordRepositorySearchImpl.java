package org.willclark.unscrambler.jpa.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.willclark.unscrambler.Letters;
import org.willclark.unscrambler.Timer;
import org.willclark.unscrambler.jpa.entity.WordEntity;
import org.willclark.unscrambler.utils.StringUtils;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WordRepositorySearchImpl implements WordRepositorySearch {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<WordEntity> findWordsContainingLetters(@NonNull final String input) {
        Timer timer = Timer.start();

        final Letters letters = new Letters(input);

        final String tempTable = "combinations_" + StringUtils.generateUniqueId();

        createTempTable(tempTable);
        insertCombinations(tempTable, letters);

        List<WordEntity> words = new ArrayList<>(0);
        words.addAll(queryWordsForCombinations(tempTable, letters));

        dropTempTable(tempTable);

        timer.stop();
        log.info(timer.duration());

        return words;
    }
    
    private void createTempTable(@NonNull String tempTable) {
        final String sql = String.format("CREATE LOCAL TEMPORARY TABLE %s (chars NVARCHAR(50) NOT NULL)", tempTable);
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    private void insertCombinations(@NonNull String tempTable, @NonNull final Letters letters) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(tempTable).append(" (chars) VALUES ");
        for (String combination : letters.getCombinations()) {
            sb.append("('").append(combination).append("'),");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        final String sql = sb.toString();

        entityManager.createNativeQuery(sql).executeUpdate();
    }

    private void dropTempTable(@NonNull String tempTable) {
        final String sql = String.format("DROP TABLE %s", tempTable);
        entityManager.createNativeQuery(sql).executeUpdate();
    }

    private List<WordEntity> queryWordsForCombinations(@NonNull String tempTable, @NonNull final Letters letters) {
        List<WordEntity> results = new ArrayList<>(0);
        
        String sql = String.format("SELECT * FROM word WHERE length >= 2 AND length <= %d AND chars IN (SELECT chars FROM %s)", letters.length(), tempTable);
        results.addAll((List<WordEntity>) entityManager.createNativeQuery(sql, WordEntity.class).getResultList());

        return results;
    }

}