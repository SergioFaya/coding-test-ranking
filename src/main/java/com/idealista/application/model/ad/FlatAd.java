package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * {@link Ad} implementation that represents a flat
 */
@Entity
public class FlatAd extends Ad {

    private static final int DESCRIPTION_LONG_POINTS = 30;
    private static final int DESCRIPTION_LONG = 50;

    private static final int DESCRIPTION_MEDIUM_POINTS = 10;
    private static final int DESCRIPTION_MEDIUM_BOT = 20;

    public FlatAd() {
        super();
    }

    public FlatAd(Integer id, String description, List<Picture> pictures, Integer size, Integer score,
                  Date irrelevantSince) {
        super(id, description, pictures, size, score, irrelevantSince);
    }

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

    @Override
    protected boolean isComplete() {
        return StringUtils.hasText(getDescription())
                && !getPictures().isEmpty()
                && getSize() > 0;
    }
}
