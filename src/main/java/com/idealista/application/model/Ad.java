package com.idealista.application.model;

import com.idealista.application.model.enums.AdTypology;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ad {

    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    private AdTypology typology;
    private String description;
    @OneToMany
    private List<Picture> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    public Ad() {
    }

    public Ad(Integer id, AdTypology typology, String description, List<Picture> pictures,
              Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
        this.id = id;
        this.typology = typology;
        this.description = description;
        this.pictures = pictures;
        this.houseSize = houseSize;
        this.gardenSize = gardenSize;
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AdTypology getTypology() {
        return this.typology;
    }

    public void setTypology(AdTypology typology) {
        this.typology = typology;
    }

    public String getDescription() {
        checkDescription();
        return this.description;
    }

    private void checkDescription() {
        if (this.description == null) {
            this.description = new String();
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Picture> getPictures() {
        checkPictures();
        return this.pictures;
    }

    private void checkPictures() {
        if (this.pictures == null) {
            this.pictures = new ArrayList<>();
        }
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Integer getHouseSize() {
        checkHouseSize();
        return this.houseSize;
    }

    private void checkHouseSize() {
        if (this.houseSize == null) {
            this.houseSize = Integer.valueOf(0);
        }
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        checkGardenSize();
        return this.gardenSize;
    }

    private void checkGardenSize() {
        if (this.gardenSize == null) {
            this.gardenSize = Integer.valueOf(0);
        }
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    public Integer getScore() {
        checkScore();
        return this.score;
    }

    private void checkScore() {
        if (this.score == null) {
            this.score = Integer.valueOf(0);
        }
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
