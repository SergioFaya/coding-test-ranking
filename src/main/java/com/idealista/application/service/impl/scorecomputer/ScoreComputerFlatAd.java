package com.idealista.application.service.impl.scorecomputer;

import com.idealista.application.service.ScoreComputerStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreComputerFlatAd extends AbstractAdScoreComputer implements ScoreComputerStrategy {

    private static final int DESCRIPTION_LONG_POINTS = 30;
    private static final int DESCRIPTION_LONG = 50;

    private static final int DESCRIPTION_MEDIUM_POINTS = 10;
    private static final int DESCRIPTION_MEDIUM_BOT = 20;

    @Override
    protected int evalDescriptionByWordCount(List<String> words) {
        int size = words.size();
        if (size >= DESCRIPTION_MEDIUM_BOT && size < DESCRIPTION_LONG) {
            return DESCRIPTION_MEDIUM_POINTS;
        } else if (size >= DESCRIPTION_LONG) {
            return DESCRIPTION_LONG_POINTS;
        }
        return NO_POINTS;
    }

}
