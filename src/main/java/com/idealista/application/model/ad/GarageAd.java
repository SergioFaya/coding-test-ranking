package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;

import java.util.Date;
import java.util.List;

/**
 * {@link Ad} implementation that represents a parking place
 */
public class GarageAd extends Ad {

    public GarageAd(Integer id, String description, List<Picture> pictures, Integer size, Integer score,
                    Date irrelevantSince) {
        super(id, description, pictures, size, score, irrelevantSince);
    }

    @Override
    protected int evalDescriptionByWordCount(List<String> words) {
        // there is no additional points by description length
        return NO_POINTS;
    }

    @Override
    protected boolean isComplete() {
        // a garage is completed by having at least a photo
        return !getPictures().isEmpty();
    }
}