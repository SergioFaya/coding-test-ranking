package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.model.enums.AdTypology;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.impl.scorecomputer.ScoreComputerChaletAd;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * {@link Ad} implementation that represents a chalet
 */
@Entity
public class ChaletAd extends Ad {

    private Integer gardenSize;

    public ChaletAd() {
        super();
    }

    public ChaletAd(Integer id, String description, List<Picture> pictures, Integer size, Integer gardenSize,
                    Integer score, Date irrelevantSince) {
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
    public void computeScore() {
        var scoreComputer = new ScoreComputerChaletAd();
        this.setScore(scoreComputer.computeScore(getDescription(), getSize(), getPictures(), isComplete()));
    }

    @Override
    public QualityAdVo createQualityAd() {
        return new QualityAdVo(this.getId(), AdTypology.CHALET.name(), this.getDescription(), this.getPictureUrls(),
                this.getSize(), this.getGardenSize(), this.getScore(), this.getIrrelevantSince());
    }

    @Override
    public PublicAdVo createPublicAd() {
        return new PublicAdVo(this.getId(), AdTypology.CHALET.name(), this.getDescription(), this.getPictureUrls(),
                this.getSize(), this.getGardenSize());
    }

    private boolean isComplete() {
        return StringUtils.hasText(getDescription())
                && !getPictures().isEmpty()
                && getSize() > 0
                && getGardenSize() > 0;
    }
}
