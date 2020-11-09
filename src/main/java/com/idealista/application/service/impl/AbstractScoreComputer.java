package com.idealista.application.service.impl;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.DescriptionKeywords;
import com.idealista.application.model.enums.PictureQuality;
import com.idealista.application.service.ScoreComputer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public abstract class AbstractScoreComputer implements ScoreComputer {

    private static final String WORD_SPLIT_REGEX = " +";

    protected static final int NO_POINTS = 0;

    private static final int HD_PIC_POINTS = 20;
    private static final int NOT_HD_PIC_POINTS = 10;
    private static final int NO_PIC_POINTS = -10;

    private static final int DESCRIPTION_POINTS = 5;

    private static final int DESCRIPTION_KEYWORD_POINTS = 10;
    private static final int COMPLETE_AD_POINTS = 40;

    private static final int MAX_SCORE = 100;
    private static final int MIN_SCORE = 0;


    public final int computeScore() {
        int score = 0;
        score += evalPictures(getPictures());
        score += evalDescription(getDescription());
        if (isComplete()) {
            score += COMPLETE_AD_POINTS;
        }
        return boundScore(score);
    }

    protected abstract int evalDescriptionByWordCount(List<String> words);

    protected abstract List<Picture> getPictures();

    protected abstract String getDescription();

    protected abstract boolean isComplete();

    private int boundScore(int score) {
        if (score > MAX_SCORE) {
            return MAX_SCORE;
        } else if (score < MIN_SCORE) {
            return MIN_SCORE;
        } else {
            return score;
        }
    }

    private int evalDescription(String description) {
        int score = 0;
        if (StringUtils.hasText(description)) {
            score += DESCRIPTION_POINTS;
        }
        var words = Arrays.asList(description.split(WORD_SPLIT_REGEX));
        score += evalDescriptionKeywords(words);
        score += evalDescriptionByWordCount(words);
        return score;
    }

    private long evalDescriptionKeywords(List<String> words) {
        var count = Arrays.asList(DescriptionKeywords.values())
                .stream()
                .filter(words::contains)
                .count();
        return count * DESCRIPTION_KEYWORD_POINTS;
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


}
