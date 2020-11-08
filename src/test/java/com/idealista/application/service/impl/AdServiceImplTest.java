package com.idealista.application.service.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.AdService;
import com.idealista.application.utils.ModelAssertion;
import org.junit.Before;
import org.junit.Test;

import static com.idealista.application.utils.RepositoryMockFactory.createAdRepositoryMock;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
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
                null, 300, null, null, null));
        ads.add(new Ad(2, "FLAT", "Nuevo ático céntrico recién reformado. " +
                "No deje pasar la oportunidad y adquiera este ático de lujo", pictures, 300,
                null, null, null));

        adService = new AdServiceImpl(createAdRepositoryMock(ads));
    }

    @Test
    public void shouldFindAllQualityAds(){
        // GIVEN
        var expected = ads.stream()
                // TODO: use factory
                .map( it -> new QualityAdVo())
                .collect(toList());
        // WHEN
        var actual = adService.findAllQualityAds();
        // THEN
        ModelAssertion.assertQualityAdVos(expected, actual);
    }

    @Test
    public void shouldFindAllPublicAds(){
        // GIVEN
        // TODO: add data for sorting
        var expected = ads.stream()
                .sorted((ad1, ad2) -> Integer.compare( ad1.getScore(), ad2.getScore()) )
                // TODO: use factory
                .map(it -> new PublicAdVo())
                .collect(toList());
        // WHEN
        var actual = adService.findAllPublicAds();
        // THEN
        ModelAssertion.assertPublicAdVos(expected, actual);
    }


}

