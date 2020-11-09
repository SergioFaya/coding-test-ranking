package com.idealista.application.model;

import com.idealista.application.model.enums.PictureQuality;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Picture {

    @Id
    private Integer id;
    private String url;
    @Enumerated(EnumType.STRING)
    private PictureQuality quality;

    public Picture() {
    }

    public Picture(Integer id, String url, PictureQuality quality) {
        this.id = id;
        this.url = url;
        this.quality = quality;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PictureQuality getQuality() {
        return this.quality;
    }

    public void setQuality(PictureQuality quality) {
        this.quality = quality;
    }
}
