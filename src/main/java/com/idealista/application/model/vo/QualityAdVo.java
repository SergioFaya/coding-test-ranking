package com.idealista.application.model.vo;

import java.util.Date;
import java.util.List;


/**
 * In depth details of the Idealista ad for the quality team
 */
public class QualityAdVo extends PublicAdVo {

    private Integer score;
    private Date irrelevantSince;

    public QualityAdVo(Integer id, String typology, String description, List<String> pictureUrls, Integer houseSize,
                       Integer gardenSize, Integer score, Date irrelevantSince) {
        super(id, typology, description, pictureUrls, houseSize, gardenSize);
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getIrrelevantSince() {
        return this.irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }
}
