package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.DescriptionKeywords;
import com.idealista.application.model.enums.PictureQuality;
import org.springframework.util.StringUtils;

import javax.persistence.MappedSuperclass;
import java.util.Arrays;
import java.util.List;

@MappedSuperclass
public abstract class AbstractScoredAd {

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

    private Integer score;

    protected AbstractScoredAd() {
    }

    protected AbstractScoredAd(Integer score) {
        this.score = score;
    }

    public final void computeScore() {
        int points = 0;
        points += evalPictures(getPictures());
        points += evalDescription(getDescription());
        if (isComplete()) {
            points += COMPLETE_AD_POINTS;
        }
        this.score = boundScore(points);
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

    public Integer getScore() {
        checkScore();
        return this.score;
    }

    private void checkScore() {
        if (this.score == null) {
            this.score = Integer.valueOf(0);
        }
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    protected abstract int evalDescriptionByWordCount(List<String> words);

    protected abstract List<Picture> getPictures();

    protected abstract String getDescription();

    protected abstract boolean isComplete();
}
