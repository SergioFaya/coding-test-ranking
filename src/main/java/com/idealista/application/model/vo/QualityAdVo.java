package com.idealista.application.model.vo;

import java.util.Date;


/**
 * In depth details of the Idealista ad for the quality team
 */
public class QualityAdVo extends PublicAdVo{

    private Integer score;
    private Date irrelevantSince;

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
