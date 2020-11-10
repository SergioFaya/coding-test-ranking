package com.idealista.application.model.ad;

import com.idealista.application.model.Picture;
import com.idealista.application.service.AdVoCreator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Common behaviour of all {@link Ad} related to different locations
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Ad implements AdVoCreator {

    @Id
    private Integer id;
    private String description;
    @OneToMany
    private List<Picture> pictures;
    private Integer size;
    private Date irrelevantSince;
    private Integer score;

    protected Ad() {
    }

    protected Ad(Integer id, String description, List<Picture> pictures,
                 Integer size, Integer score, Date irrelevantSince) {
        this.id = id;
        this.description = description;
        this.pictures = pictures;
        this.size = size;
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }


    public abstract void computeScore();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        checkDescription();
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void checkDescription() {
        if (this.description == null) {
            this.description = "";
        }
    }

    public List<Picture> getPictures() {
        checkPictures();
        return this.pictures;
    }

    public List<String> getPictureUrls() {
        return getPictures().stream().map(Picture::getUrl).collect(toList());
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    private void checkPictures() {
        if (this.pictures == null) {
            this.pictures = new ArrayList<>();
        }
    }

    public Integer getSize() {
        checkSize();
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    private void checkSize() {
        if (this.size == null) {
            this.size = Integer.valueOf(0);
        }
    }

    public Date getIrrelevantSince() {
        return this.irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
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

}
