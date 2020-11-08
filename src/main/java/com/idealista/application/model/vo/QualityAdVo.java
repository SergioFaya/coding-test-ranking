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

    public QualityAdVo(Ad ad) {
        Assert.notNull(ad, "Ad cannot be null when building QualityAd");
        this.setId(ad.getId());
        this.setTypology(ad.getTypology());
        this.setDescription(ad.getDescription());
        this.setPictures(ad.getPictures());
        this.setHouseSize(ad.getHouseSize());
        this.setGardenSize(ad.getGardenSize());
        this.setScore(ad.getScore());
        this.setIrrelevantSince(ad.getIrrelevantSince());
    }

    private void setPictures(List<Picture> pictures) {
        if (pictures != null) {
            var pictureUrls = pictures
                    .stream()
                    .map(Picture::getUrl).collect(toList());

            this.setPictureUrls(pictureUrls);
        }
    }

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
