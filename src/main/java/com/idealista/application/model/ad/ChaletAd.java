package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.AdTypology;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * {@link Ad} implementation that represents a chalet
 */
@Entity
public class ChaletAd extends Ad {

    private static final int DESCRIPTION_LONG_POINTS = 20;
    private static final int DESCRIPTION_LONG = 50;

    private Integer gardenSize;

    public ChaletAd() {
        super();
    }

    @Override
    public AdTypology getTypology() {
        return AdTypology.CHALET;
    }

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
