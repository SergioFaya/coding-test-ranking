package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.AdTypology;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.impl.scorecomputer.ScoreComputerFlatAd;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * {@link Ad} implementation that represents a flat
 */
@Entity
public class FlatAd extends Ad {

    public FlatAd() {
        super();
    }

    public FlatAd(Integer id, String description, List<Picture> pictures, Integer size, Integer score,
                  Date irrelevantSince) {
        super(id, description, pictures, size, score, irrelevantSince);
    }

    @Override
    public void computeScore() {
        var scoreComputer = new ScoreComputerFlatAd();
        this.setScore(scoreComputer.computeScore(getDescription(), getSize(), getPictures(), isComplete()));
    }

    @Override
    public QualityAdVo createQualityAd() {
        return new QualityAdVo(this.getId(), AdTypology.FLAT.name(), this.getDescription(), getPictureUrls(),
                this.getSize(), 0, this.getScore(), this.getIrrelevantSince());
    }

    @Override
    public PublicAdVo createPublicAd() {
        return new PublicAdVo(this.getId(), AdTypology.FLAT.name(), this.getDescription(), getPictureUrls(),
                this.getSize(), 0);
    }

    private boolean isComplete() {
        return StringUtils.hasText(getDescription())
                && !getPictures().isEmpty()
                && getSize() > 0;
    }
}
