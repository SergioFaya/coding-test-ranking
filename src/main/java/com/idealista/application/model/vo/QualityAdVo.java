package com.idealista.application.model.vo;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class QualityAdVo {

    private Integer id;
    private String typology;
    private String description;
    private List<String> pictureUrls;
    private Integer houseSize;
    private Integer gardenSize;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
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
