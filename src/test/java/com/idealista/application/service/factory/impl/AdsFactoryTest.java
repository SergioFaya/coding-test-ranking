package com.idealista.application.service.factory.impl;

import com.idealista.application.model.Picture;
import com.idealista.application.model.ad.Ad;
import com.idealista.application.model.ad.ChaletAd;
import com.idealista.application.model.ad.FlatAd;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.factory.AdsFactory;
import com.idealista.application.utils.ModelAssertion;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static com.idealista.application.model.enums.PictureQuality.HD;
import static com.idealista.application.model.enums.PictureQuality.SD;
import static java.util.stream.Collectors.toList;


public class AdsFactoryTest {

    private Ad defaultAd_withPictures;
    private ChaletAd defaultAd_noPictures;

    @Before
    public void setUp() {
        new Picture(4, "url", HD);
        this.defaultAd_noPictures = new ChaletAd(1, "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(), 300, 10, 100, new Date());
        this.defaultAd_withPictures = new FlatAd(1, "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(new Picture(1, "url", HD), new Picture(2, "url", SD)),
                300, 100, new Date());
    }

    @Test
    public void shouldCreatePublicAdVo_noPictures() {
        // GIVEN
        var expected = new PublicAdVo();
        expected.setId(this.defaultAd_noPictures.getId());
        expected.setDescription(this.defaultAd_noPictures.getDescription());
        expected.setGardenSize(this.defaultAd_noPictures.getGardenSize());
        expected.setHouseSize(this.defaultAd_noPictures.getSize());
        expected.setTypology(this.defaultAd_noPictures.getTypology().name());
        // WHEN
        var actual = AdsFactory.createPublicAdVo(this.defaultAd_noPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected, actual);
    }

    @Test
    public void shouldCreatePublicAdVo_withPictures() {
        // GIVEN
        var expected = new PublicAdVo();
        expected.setId(this.defaultAd_withPictures.getId());
        expected.setDescription(this.defaultAd_withPictures.getDescription());
        expected.setHouseSize(this.defaultAd_withPictures.getSize());
        expected.setTypology(this.defaultAd_withPictures.getTypology().name());
        expected.setPictureUrls(this.defaultAd_withPictures.getPictures().stream().map(Picture::getUrl).collect(toList()));
        // WHEN
        var actual = AdsFactory.createPublicAdVo(this.defaultAd_withPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected, actual);
    }

    @Test
    public void shouldCreateQualityAdVo_noPictures() {
        // GIVEN
        var expected = new QualityAdVo();
        expected.setId(this.defaultAd_noPictures.getId());
        expected.setDescription(this.defaultAd_noPictures.getDescription());
        expected.setGardenSize(this.defaultAd_noPictures.getGardenSize());
        expected.setHouseSize(this.defaultAd_noPictures.getSize());
        expected.setTypology(this.defaultAd_noPictures.getTypology().name());
        // WHEN
        var actual = AdsFactory.createQualityAdVo(this.defaultAd_noPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected, actual);
    }

    @Test
    public void shouldCreateQualityAdVo_withPictures() {
        // GIVEN
        var expected = new QualityAdVo();
        expected.setId(this.defaultAd_withPictures.getId());
        expected.setDescription(this.defaultAd_withPictures.getDescription());
        expected.setHouseSize(this.defaultAd_withPictures.getSize());
        expected.setTypology(this.defaultAd_withPictures.getTypology().name());
        expected.setPictureUrls(this.defaultAd_withPictures.getPictures().stream().map(Picture::getUrl).collect(toList()));
        expected.setScore(this.defaultAd_withPictures.getScore());
        expected.setIrrelevantSince(this.defaultAd_withPictures.getIrrelevantSince());
        // WHEN
        var actual = AdsFactory.createQualityAdVo(this.defaultAd_withPictures);
        // THEN
        ModelAssertion.assertPublicAdVo(expected, actual);
    }

}
