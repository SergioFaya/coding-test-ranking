package com.idealista.application.service.impl.scorecomputer;

import com.idealista.application.service.ScoreComputerStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreComputerChaletAd extends AbstractAdScoreComputer implements ScoreComputerStrategy {

    private static final int DESCRIPTION_LONG_POINTS = 20;
    private static final int DESCRIPTION_LONG = 50;

    @Override
    protected int evalDescriptionByWordCount(List<String> words) {
        if (words.size() > DESCRIPTION_LONG) {
            return DESCRIPTION_LONG_POINTS;
        }
        return NO_POINTS;
    }

}
