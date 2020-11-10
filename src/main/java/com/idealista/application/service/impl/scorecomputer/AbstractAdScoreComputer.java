package com.idealista.application.service.impl.scorecomputer;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.DescriptionKeywords;
import com.idealista.application.model.enums.PictureQuality;
import com.idealista.application.service.ScoreComputerStrategy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public abstract class AbstractAdScoreComputer implements ScoreComputerStrategy {

    private static final String WORD_SPLIT_REGEX = " +";

    protected static final int NO_POINTS = 0;

    private static final int HD_PIC_POINTS = 20;
    private static final int NOT_HD_PIC_POINTS = 10;
    private static final int NO_PIC_POINTS = -10;

    private static final int DESCRIPTION_POINTS = 5;

    private static final int DESCRIPTION_KEYWORD_POINTS = 5;
    private static final int COMPLETE_AD_POINTS = 40;

    private static final int MAX_SCORE = 100;
    private static final int MIN_SCORE = 0;

    @Override
    public int computeScore(String description, Integer size, List<Picture> pictures, boolean isComplete) {
        int points = 0;
        points += evalPictures(pictures);
        points += evalDescription(description);
        if (isComplete) {
            points += COMPLETE_AD_POINTS;
        }
        return boundScore(points);
    }

    private int boundScore(int points) {
        if (points > MAX_SCORE) {
            return MAX_SCORE;
        } else if (points < MIN_SCORE) {
            return MIN_SCORE;
        } else {
            return points;
        }
    }

    private int evalDescription(String description) {
        int points = 0;
        if (StringUtils.hasText(description)) {
            points += DESCRIPTION_POINTS;
            var words = Arrays.asList(description.split(WORD_SPLIT_REGEX));
            points += evalDescriptionKeywords(words);
            points += evalDescriptionByWordCount(words);
        }
        return points;
    }

    private int evalDescriptionKeywords(List<String> words) {
        int points = 0;
        for (DescriptionKeywords value : DescriptionKeywords.values()) {
            var string = value.getValue();
            if (words.contains(string)) {
                points++;
            }
        }
        return points * DESCRIPTION_KEYWORD_POINTS;
    }

    private int evalPictures(List<Picture> pictures) {
        return pictures.stream()
                .map(this::evalPicture)
                .reduce((i1, i2) -> i1 + i2)
                .orElse(NO_PIC_POINTS);
    }

    protected int evalPicture(Picture picture) {
        if (picture.getQuality().equals(PictureQuality.HD)) {
            return HD_PIC_POINTS;
        } else {
            return NOT_HD_PIC_POINTS;
        }
    }

    protected abstract int evalDescriptionByWordCount(List<String> words);

}
