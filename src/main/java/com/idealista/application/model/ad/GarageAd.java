package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.AdTypology;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.impl.ScoreComputerGarageAd;

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
    public void computeScore() {
        var scoreComputer = new ScoreComputerGarageAd();
        setScore(scoreComputer.computeScore(getDescription(), getSize(), getPictures(), isComplete()));
    }

    @Override
    public QualityAdVo createQualityAd() {
        return new QualityAdVo(this.getId(), AdTypology.GARAGE.name(), this.getDescription(), getPictureUrls(),
                this.getSize(), 0, this.getScore(), this.getIrrelevantSince());
    }

    @Override
    public PublicAdVo createPublicAd() {
        return new PublicAdVo(this.getId(), AdTypology.GARAGE.name(), this.getDescription(), getPictureUrls(),
                this.getSize(), 0);
    }

    private boolean isComplete() {
        return !getPictures().isEmpty();
    }
}