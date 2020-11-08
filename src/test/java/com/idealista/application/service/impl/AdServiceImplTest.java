package com.idealista.application.service.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.application.service.impl.utils.ModelAssertion;
import com.idealista.infrastructure.api.QualityAd;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.idealista.application.service.impl.utils.RepositoryMockFactory.createAdRepositoryMock;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
    @Ignore
    public void shouldFindAll(){
        List<Ad> actual = adService.findAll();
        ModelAssertion.assertAds(ads, actual);
    }


}

