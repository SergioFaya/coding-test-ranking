package com.idealista.application.service.impl.utils;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Util class with assertions for model objects
 */
public class ModelAssertion {

    public static void assertPublicAdVos(List<PublicAdVo> expected, List<PublicAdVo> actual) {
        if(expected != null && actual!= null) {
            assertEquals(expected.size(), actual.size());
            IntStream
                    .range(0, expected.size())
                    .mapToObj(i -> Pair.of(expected.get(i), actual.get(i)))
                    .forEach(it -> assertPublicAdVo(it.getFirst(), it.getSecond()));
        }
    }

    public static void assertPublicAdVo(PublicAdVo expected, PublicAdVo actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTypology(), actual.getTypology());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPictureUrls(), actual.getPictureUrls());
        assertEquals(expected.getHouseSize(), actual.getHouseSize());
        assertEquals(expected.getGardenSize(), actual.getGardenSize());
    }


    public static void assertQualityAdVos(List<QualityAdVo> expected, List<QualityAdVo> actual){
        if(expected != null && actual!= null) {
            assertEquals(expected.size(), actual.size());
            IntStream
                    .range(0, expected.size())
                    .mapToObj(i -> Pair.of(expected.get(i), actual.get(i)))
                    .forEach(it -> assertQualityAdVo(it.getFirst(), it.getSecond()));
        }
    }

    public static void assertQualityAdVo(QualityAdVo expected, QualityAdVo actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTypology(), actual.getTypology());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPictureUrls(), actual.getPictureUrls());
        assertEquals(expected.getHouseSize(), actual.getHouseSize());
        assertEquals(expected.getGardenSize(), actual.getGardenSize());
        assertEquals(expected.getScore(), actual.getScore());
        assertEquals(expected.getIrrelevantSince(), actual.getIrrelevantSince());
    }

    public static void assertAds(List<Ad> expected, List<Ad> actual) {
        if(expected != null && actual!= null) {
            assertEquals(expected.size(), actual.size());
            IntStream
                    .range(0, expected.size())
                    .mapToObj(i -> Pair.of(expected.get(i), actual.get(i)))
                    .forEach(it -> assertAd(it.getFirst(), it.getSecond()));
        }
    }

    public static void assertAd(Ad expected, Ad actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTypology(), actual.getTypology());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertPictures(expected.getPictures(), actual.getPictures());
        assertEquals(expected.getHouseSize(), actual.getHouseSize());
        assertEquals(expected.getGardenSize(), actual.getGardenSize());
        assertEquals(expected.getScore(), actual.getScore());
        assertEquals(expected.getIrrelevantSince(), actual.getIrrelevantSince());

    }

    public static void assertPictures(List<Picture> expected, List<Picture> actual){
        if(expected != null && actual!= null){
            assertEquals(expected.size(), actual.size());
            IntStream
                    .range(0, expected.size())
                    .mapToObj(i -> Pair.of(expected.get(i),actual.get(i)))
                    .forEach(it -> assertPicture(it.getFirst(),it.getSecond()));
        }
    }

    public static void assertPicture(Picture expected, Picture actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUrl(), actual.getUrl());
        assertEquals(expected.getQuality(), actual.getQuality());
    }

}
