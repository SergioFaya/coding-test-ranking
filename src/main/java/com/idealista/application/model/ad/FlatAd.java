package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.AdTypology;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
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
    public AdTypology getTypology() {
        return AdTypology.FLAT;
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


    @Override
    public QualityAdVo createQualityAd() {
        return new QualityAdVo(this.getId(), this.getTypology().name(), this.getDescription(), getPictureUrls(),
                this.getSize(), 0, this.getScore(), this.getIrrelevantSince());
    }

    @Override
    public PublicAdVo createPublicAd() {
        return new PublicAdVo(this.getId(), this.getTypology().name(), this.getDescription(), getPictureUrls(),
                this.getSize(), 0);
    }
}
