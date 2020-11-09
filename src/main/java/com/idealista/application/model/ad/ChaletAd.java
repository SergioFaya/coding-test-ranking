package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

public class ChaletAd extends Ad {

    private static final int DESCRIPTION_LONG_POINTS = 20;
    private static final int DESCRIPTION_LONG = 50;

    private Integer gardenSize;

    public ChaletAd(Integer id, String description, List<Picture> pictures, Integer size, Integer gardenSize,
                    Integer score,
                    Date irrelevantSince) {
        super(id, description, pictures, size, score, irrelevantSince);
        this.gardenSize = gardenSize;
    }

    public Integer getGardenSize() {
        return this.gardenSize;
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    @Override
    protected int evalDescriptionByWordCount(List<String> words) {
        if (words.size() > DESCRIPTION_LONG) {
            return DESCRIPTION_LONG_POINTS;
        }
        return NO_POINTS;
    }

    @Override
    protected boolean isComplete() {
        return StringUtils.hasText(getDescription())
                && !getPictures().isEmpty()
                && getSize() > 0
                && getGardenSize() > 0;
    }
}
