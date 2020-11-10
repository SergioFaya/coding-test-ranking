package com.idealista.application.service.impl;

import com.idealista.application.service.ScoreComputerStrategy;
import com.idealista.application.service.impl.scorecomputer.AbstractAdScoreComputer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreComputerGarageAd extends AbstractAdScoreComputer implements ScoreComputerStrategy {

    @Override
    protected int evalDescriptionByWordCount(List<String> words) {
        return NO_POINTS;
    }

}
