package com.idealista.application.service.factory.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.factory.AdsFactory;
import com.idealista.application.utils.ModelAssertion;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static java.util.stream.Collectors.toList;


public class AdsFactoryTest {

    private Ad defaultAd_withPictures;
    private Ad defaultAd_noPictures;

    @Before
    public void setUp(){
        new Picture(4, "url", "HD");
        this.defaultAd_noPictures = new Ad(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(), 300, 10, 100, new Date());
        this.defaultAd_withPictures = new Ad(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(new Picture(1, "url", "HD"), new Picture(2, "url", "SD")),
                300, 17, 10, new Date());
    }

    @Test
    public void shouldCreatePublicAdVo_noPictures() {
        // GIVEN
        var expected = new PublicAdVo();
        expected.setId(defaultAd_noPictures.getId());
        expected.setDescription(defaultAd_noPictures.getDescription());
        expected.setGardenSize(defaultAd_noPictures.getGardenSize());
        expected.setHouseSize(defaultAd_noPictures.getHouseSize());
        expected.setTypology(defaultAd_noPictures.getTypology());
        // WHEN
        var actual = AdsFactory.createPublicAdVo(defaultAd_noPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected,actual);
    }

    @Test
    public void shouldCreatePublicAdVo_withPictures() {
        // GIVEN
        var expected = new PublicAdVo();
        expected.setId(defaultAd_withPictures.getId());
        expected.setDescription(defaultAd_withPictures.getDescription());
        expected.setGardenSize(defaultAd_withPictures.getGardenSize());
        expected.setHouseSize(defaultAd_withPictures.getHouseSize());
        expected.setTypology(defaultAd_withPictures.getTypology());
        expected.setPictureUrls(defaultAd_withPictures.getPictures().stream().map(Picture::getUrl).collect(toList()));
        // WHEN
        var actual = AdsFactory.createPublicAdVo(defaultAd_withPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected,actual);
    }

    @Test
    public void shouldCreateQualityAdVo_noPictures() {
        // GIVEN
        var expected = new QualityAdVo();
        expected.setId(defaultAd_noPictures.getId());
        expected.setDescription(defaultAd_noPictures.getDescription());
        expected.setGardenSize(defaultAd_noPictures.getGardenSize());
        expected.setHouseSize(defaultAd_noPictures.getHouseSize());
        expected.setTypology(defaultAd_noPictures.getTypology());
        // WHEN
        var actual = AdsFactory.createQualityAdVo(defaultAd_noPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected,actual);
    }

    @Test
    public void shouldCreateQualityAdVo_withPictures() {
        // GIVEN
        var expected = new QualityAdVo();
        expected.setId(defaultAd_withPictures.getId());
        expected.setDescription(defaultAd_withPictures.getDescription());
        expected.setGardenSize(defaultAd_withPictures.getGardenSize());
        expected.setHouseSize(defaultAd_withPictures.getHouseSize());
        expected.setTypology(defaultAd_withPictures.getTypology());
        expected.setPictureUrls(defaultAd_withPictures.getPictures().stream().map(Picture::getUrl).collect(toList()));
        expected.setScore(defaultAd_withPictures.getScore());
        expected.setIrrelevantSince(defaultAd_withPictures.getIrrelevantSince());
        // WHEN
        var actual = AdsFactory.createQualityAdVo(defaultAd_withPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected,actual);
    }

}
