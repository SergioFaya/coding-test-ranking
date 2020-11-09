package com.idealista.application.service.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.service.AdService;
import com.idealista.application.service.factory.AdsFactory;
import com.idealista.application.utils.ModelAssertion;
import org.junit.Before;
import org.junit.Test;

import static com.idealista.application.utils.RepositoryMockFactory.createAdRepositoryMock;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdServiceImplTest {

    private AdService adService;

    private List<Ad> ads;
    private List<Picture> pictures;
    
    @Before
    public void setUp(){
        ads = new ArrayList<>();
        pictures = new ArrayList<>();

        pictures.add(new Picture(4, "url", "HD"));
        ads.add(new Ad(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(), 300, 100, 30, new Date()));
        ads.add(new Ad(2, "FLAT", "Nuevo ático céntrico recién reformado. " +
                "No deje pasar la oportunidad y adquiera este ático de lujo", pictures, 300,
                10, 100, new Date()));

        adService = new AdServiceImpl(createAdRepositoryMock(ads));
    }

    @Test
    public void shouldFindAllQualityAds(){
        // GIVEN
        var expected = ads.stream()
                .map( ad -> AdsFactory.createQualityAdVo(ad))
                .collect(toList());
        // WHEN
        var actual = adService.findAllQualityAds();
        // THEN
        ModelAssertion.assertQualityAdVos(expected, actual);
    }

    @Test
    public void shouldFindAllPublicAds(){
        // GIVEN
        var expected = ads.stream()
                .sorted((ad1, ad2) -> Integer.compare(ad2.getScore(), ad1.getScore()))
                .map( ad -> AdsFactory.createPublicAdVo(ad))
                .collect(toList());
        // WHEN
        var actual = adService.findAllPublicAds();
        // THEN
        ModelAssertion.assertPublicAdVos(expected, actual);
    }

}

