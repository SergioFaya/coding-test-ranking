package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.AdTypology;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * {@link Ad} implementation that represents a parking place
 */
@Entity
public class GarageAd extends Ad {

    public GarageAd() {
        super();
    }

    public GarageAd(Integer id, String description, List<Picture> pictures, Integer size, Integer score,
                    Date irrelevantSince) {
        super(id, description, pictures, size, score, irrelevantSince);
    }

    @Override
    public AdTypology getTypology() {
        return AdTypology.GARAGE;
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