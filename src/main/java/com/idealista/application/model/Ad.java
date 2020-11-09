package com.idealista.application.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ad {

    @Id
    private Integer id;
    private String typology;
    private String description;
    @OneToMany
    private List<Picture> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    public Ad() {}

    public Ad(Integer id, String typology, String description, List<Picture> pictures,
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
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypology() {
        checkTypology();
        return typology;
    }

    private void checkTypology(){
        if (this.typology == null){
            this.typology = new String();
        }
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getDescription() {
        checkDescription();
        return description;
    }

    private void checkDescription(){
        if (this.description == null){
            this.description = new String();
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Picture> getPictures() {
        checkPictures();
        return pictures;
    }

    private void checkPictures() {
        if (pictures == null){
            this.pictures = new ArrayList<>();
        }
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Integer getHouseSize() {
        checkHouseSize();
        return houseSize;
    }

    private void checkHouseSize() {
        if(this.houseSize == null){
            this.houseSize = Integer.valueOf(0);
        }
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        checkGardenSize();
        return gardenSize;
    }

    private void checkGardenSize() {
        if(this.gardenSize == null){
            this.gardenSize = Integer.valueOf(0);
        }
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    public Integer getScore() {
        checkScore();
        return score;
    }

    private void checkScore() {
        if (this.score == null){
            this.score = Integer.valueOf(0);
        }
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
