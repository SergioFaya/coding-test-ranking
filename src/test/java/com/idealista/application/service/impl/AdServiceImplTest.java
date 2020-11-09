package com.idealista.application.service.impl;

import com.idealista.application.model.Ad;
import com.idealista.application.model.Picture;
import com.idealista.application.service.AdService;
import com.idealista.application.service.factory.AdsFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.idealista.application.model.enums.AdTypology.*;
import static com.idealista.application.model.enums.DescriptionKeywords.*;
import static com.idealista.application.model.enums.PictureQuality.HD;
import static com.idealista.application.model.enums.PictureQuality.SD;
import static com.idealista.application.utils.ModelAssertion.*;
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

        this.adService = new AdServiceImpl(createAdRepositoryMock(this.ads, null));
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
        assertQualityAdVos(expected, actual);
    }

    @Test
    public void shouldFindAllPublicAds() {
        // GIVEN
        var expected = this.ads.stream()
                .sorted((ad1, ad2) -> Integer.compare(ad2.getScore(), ad1.getScore()))
                .map(ad -> AdsFactory.createPublicAdVo(ad))
                .collect(toList());
        // WHEN
        var actual = this.adService.findAllPublicAds();
        // THEN
        assertPublicAdVos(expected, actual);
    }

    @Test
    public void shouldAssignScoreForAllAds_noPictures() {
        // GIVEN
        var updated = new ArrayList<Ad>();
        var expected = Arrays.asList(new Ad(1, FLAT, "",
                Arrays.asList(), 10, 10, 0, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(this.ads, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(expected, updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_HDPhoto() {
        // GIVEN
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        var expected = Arrays.asList(new Ad(1, FLAT, "",
                Arrays.asList(), 10, 10, 20, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(this.ads, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(expected, updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_SDPhoto() {
        // GIVEN
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", SD));
        var expected = Arrays.asList(new Ad(1, FLAT, "",
                Arrays.asList(), 10, 10, 10, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(this.ads, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(expected, updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_HDandSDPhoto() {
        // GIVEN
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        var expected = Arrays.asList(new Ad(1, FLAT, "",
                Arrays.asList(), 10, 10, 30, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(this.ads, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(expected, updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_flat_DescriptionShort() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, FLAT, "description",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_flat_Description19Words() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, FLAT, "Duis feugiat nibh non nulla mollis pellentesque. Nullam quis " +
                "arcu augue. Suspendisse at dolor eget massa facilisis aliquet ut.",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }


    @Test
    public void shouldAssignScoreForAllAds_flat_Description20Words() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, FLAT, "Duis feugiat nibh non nulla mollis pellentesque. Nullam quis " +
                "arcu augue. Suspendisse at dolor eget massa facilisis aliquet eu ut.",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_flat_Description49Words() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, FLAT, "Nulla accumsan, elit vestibulum ultricies aliquet, mi mi " +
                "lacinia massa, nec vestibulum lacus turpis ac tortor. Fusce nulla nunc, malesuada in nisi in, cursus" +
                " auctor lectus.  Vestibulum laoreet purus felis, eu varius nulla efficitur sit amet. Donec auctor " +
                "ultrices ex, ac scelerisque  lorem malesuada in. Donec accumsan eget ipsum.",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_flat_Description50Words() {
        // GIVEN
        var expectedScore = 30;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, FLAT, "Sed et vulputate est, non faucibus ligula. Phasellus tempus pretium ligula, " +
                "non ullamcorper neque. Fusce tristique neque id tempus ornare. Nunc tincidunt libero id dolor " +
                "lobortis, at dictum neque hendrerit. Suspendisse sit amet bibendum dolor, non cursus orci. Duis " +
                "dapibus purus eget sem scelerisque, nec pellentesque ipsum consectetur. Mauris in.",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_flat_Description80Words() {
        // GIVEN
        var expectedScore = 30;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, FLAT, "Aliquam vehicula enim eu turpis placerat venenatis. Ut vel " +
                "sapien sed sapien porta ultrices ac nec felis. Donec ac ultrices erat, a efficitur magna. Cras " +
                "posuere mollis ligula ut sodales. Fusce efficitur arcu nec nulla accumsan finibus. Aenean mi mi, " +
                "accumsan sit amet vulputate eu, maximus ac ante. Class aptent taciti sociosqu ad litora torquent per" +
                " conubia nostra, per inceptos himenaeos. Suspendisse non bibendum lacus. Etiam pulvinar ornare diam," +
                " in commodo dolor ultrices quis. Nunc ornare orci a pulvinar auctor.",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_chalet_DescriptionShort() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "description",
                Arrays.asList(), 10, 10, null, new Date()));

        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_chalet_Description19Words() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "Pellentesque sed eros non nisi tincidunt efficitur sed sit " +
                "amet lorem. Nunc eget mi nec diam vehicula vulputate. Integer.",
                Arrays.asList(), 10, 10, null, new Date()));

        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }


    @Test
    public void shouldAssignScoreForAllAds_chalet_Description20Words() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "Duis feugiat nibh non nulla mollis pellentesque. Nullam quis " +
                "arcu augue. Suspendisse at dolor eget massa facilisis aliquet eu ut.",
                Arrays.asList(), 10, 10, null, new Date()));

        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_chalet_Description49Words() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "Nulla accumsan, elit vestibulum ultricies aliquet, mi mi " +
                "lacinia massa, nec vestibulum lacus turpis ac tortor. Fusce nulla nunc, malesuada in nisi in, cursus" +
                " auctor lectus.  Vestibulum laoreet purus felis, eu varius nulla efficitur sit amet. Donec auctor " +
                "ultrices ex, ac scelerisque  lorem malesuada in. Donec accumsan eget ipsum.",
                Arrays.asList(), 10, 10, null, new Date()));

        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_chalet_Description50Words() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "Sed et vulputate est, non faucibus ligula. Phasellus tempus " +
                "pretium ligula, " +
                "non ullamcorper neque. Fusce tristique neque id tempus ornare. Nunc tincidunt libero id dolor " +
                "lobortis, at dictum neque hendrerit. Suspendisse sit amet bibendum dolor, non cursus orci. Duis " +
                "dapibus purus eget sem scelerisque, nec pellentesque ipsum consectetur. Mauris in.",
                Arrays.asList(), 10, 10, null, new Date()));

        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_chalet_Description80Words() {
        // GIVEN
        var expectedScore = 20;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "Aliquam vehicula enim eu turpis placerat venenatis. Ut vel " +
                "sapien sed sapien porta ultrices ac nec felis. Donec ac ultrices erat, a efficitur magna. Cras " +
                "posuere mollis ligula ut sodales. Fusce efficitur arcu nec nulla accumsan finibus. Aenean mi mi, " +
                "accumsan sit amet vulputate eu, maximus ac ante. Class aptent taciti sociosqu ad litora torquent per" +
                " conubia nostra, per inceptos himenaeos. Suspendisse non bibendum lacus. Etiam pulvinar ornare diam," +
                " in commodo dolor ultrices quis. Nunc ornare orci a pulvinar auctor.",
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }


    @Test
    public void shouldAssignScoreForAllAds_Description19WordsWithKeyWord_Luminoso() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "descripcion " + BRIGHT,
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_Description19WordsWithKeyWord_Nuevo() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "descripcion " + NEW,
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_Description19WordsWithKeyWord_Centrico() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "descripcion " + DOWNTOWN,
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_Description19WordsWithKeyWord_Reformado() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "descripcion " + REFORMED,
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_Description19WordsWithKeyWord_Atico() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var data = Arrays.asList(new Ad(1, CHALET, "descripcion " + ATTIC,
                Arrays.asList(), 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_AdCompleteFlat() {
        // GIVEN
        var expectedScore = 40 + 5 + 20;
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        var data = Arrays.asList(new Ad(1, FLAT, "piso completo",
                pictures, 10, null, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_AdCompleteChalet() {
        // GIVEN
        var expectedScore = 40 + 5 + 20;
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        var data = Arrays.asList(new Ad(1, CHALET, "chalet completo",
                pictures, 100, 12, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }

    @Test
    public void shouldAssignScoreForAllAds_AdCompleteGarage() {
        // GIVEN
        var expectedScore = 40 + 5 + 20;
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        var data = Arrays.asList(new Ad(1, GARAGE, "",
                pictures, 10, 10, null, new Date()));
        this.adService = new AdServiceImpl(createAdRepositoryMock(data, updated));
        // WHEN
        this.adService.assignScoreForAllAds();
        // THEN
        assertAds(data.stream().map(ad -> {
            ad.setScore(expectedScore);
            return ad;
        }).collect(toList()), updated);
    }
}

