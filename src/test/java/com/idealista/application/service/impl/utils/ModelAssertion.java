package com.idealista.application.service.impl.utils;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class ModelAssertion {

    public static void assertAds(List<Ad> expected, List<Ad> actual) {
        assertEquals(expected.size(), actual.size());
        IntStream
                .range(0, expected.size())
                .mapToObj(i -> Pair.of(expected.get(i),actual.get(i)))
                .forEach(it -> assertAd(it.getFirst(),it.getSecond()));
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
        assertEquals(expected.size(), actual.size());
        IntStream
                .range(0, expected.size())
                .mapToObj(i -> Pair.of(expected.get(i),actual.get(i)))
                .forEach(it -> assertPicture(it.getFirst(),it.getSecond()));
    }

    public static void assertPicture(Picture expected, Picture actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUrl(), actual.getUrl());
        assertEquals(expected.getQuality(), actual.getQuality());
    }
}
