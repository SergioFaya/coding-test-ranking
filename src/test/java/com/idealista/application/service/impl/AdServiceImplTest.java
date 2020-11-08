package com.idealista.application.service.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.AdService;
import com.idealista.application.service.impl.utils.ModelAssertion;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static com.idealista.application.service.impl.utils.RepositoryMockFactory.createAdRepositoryMock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdServiceImplTest {

    private AdService adService;

    List<Ad> ads;
    List<Picture> pictures;
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
    public void shouldFindAll(){
        // GIVEN
        var expected = ads.stream()
                .map( it -> new QualityAdVo(it))
                .collect(Collectors.toList());
        // WHEN
        var actual = adService.findAllQualityAds();
        // THEN
        ModelAssertion.assertQualityAdVos(expected, actual);
    }


}

