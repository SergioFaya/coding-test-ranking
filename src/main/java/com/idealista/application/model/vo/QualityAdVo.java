package com.idealista.application.model.vo;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


/**
 * In depth details of the Idealista ad for the quality team
 */
public class QualityAdVo extends PublicAdVo{

    private Integer score;
    private Date irrelevantSince;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }
}
