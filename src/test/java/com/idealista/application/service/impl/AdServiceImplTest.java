package com.idealista.application.service.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.service.AdService;
import com.idealista.application.service.factory.AdsFactory;
import com.idealista.application.utils.ModelAssertion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.idealista.application.model.enums.AdTypology.CHALET;
import static com.idealista.application.model.enums.AdTypology.FLAT;
import static com.idealista.application.model.enums.PictureQuality.HD;
import static com.idealista.application.model.enums.PictureQuality.SD;
import static com.idealista.application.utils.RepositoryMockFactory.createAdRepositoryMock;
import static java.util.stream.Collectors.toList;

public class AdServiceImplTest {

    private AdService adService;

    private List<Ad> ads;
    private List<Picture> pictures;

    @Before
    public void setUp() {
        this.ads = new ArrayList<>();
        this.pictures = new ArrayList<>();

        this.pictures.add(new Picture(4, "url", HD));
        this.ads.add(new Ad(1, CHALET, "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(), 300, 100, 30, new Date()));
        this.ads.add(new Ad(2, FLAT, "Nuevo ático céntrico recién reformado. " +
                "No deje pasar la oportunidad y adquiera este ático de lujo", this.pictures, 300,
                10, 100, new Date()));

        this.adService = new AdServiceImpl(createAdRepositoryMock(this.ads));
    }

    @Test
    public void shouldFindAllQualityAds() {
        // GIVEN
        var expected = this.ads.stream()
                .map(ad -> AdsFactory.createQualityAdVo(ad))
                .collect(toList());
        // WHEN
        var actual = this.adService.findAllQualityAds();
        // THEN
        ModelAssertion.assertQualityAdVos(expected, actual);
    }

    @Test
    public void shouldFindAllPublicAds(){
        // GIVEN
        var expected = this.ads.stream()
                .sorted((ad1, ad2) -> Integer.compare(ad2.getScore(), ad1.getScore()))
                .map(ad -> AdsFactory.createPublicAdVo(ad))
                .collect(toList());
        // WHEN
        var actual = this.adService.findAllPublicAds();
        // THEN
        ModelAssertion.assertPublicAdVos(expected, actual);
    }

    @Test
    public void shouldAssignScoreForAllAds_noPictures() {
        // GIVEN
        var expected = new Ad(1, FLAT, "",
                Arrays.asList(), 10, 10, 100, new Date());

    }

    @Test
    public void shouldAssignScoreForAllAds_HDAndNotHDPhotos() {
        // GIVEN
        var pictures = Arrays.asList(new Picture(1, "url", HD), new Picture(2, "url", SD));
        var expected = new Ad(1, FLAT, "",
                pictures, 10, 10, 100, new Date());

    }
}

