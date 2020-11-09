package com.idealista.application.service.impl;

import com.idealista.application.model.Picture;
import com.idealista.application.model.ad.Ad;
import com.idealista.application.model.ad.ChaletAd;
import com.idealista.application.model.ad.FlatAd;
import com.idealista.application.model.ad.GarageAd;
import com.idealista.application.service.AdService;
import com.idealista.application.service.factory.AdsFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        this.ads.add(new ChaletAd(1, "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Arrays.asList(), 300, 100, 30, new Date()));
        this.ads.add(new FlatAd(2, "Nuevo ático céntrico recién reformado. " +
                "No deje pasar la oportunidad y adquiera este ático de lujo", this.pictures, 300,
                100, new Date()));
        this.ads.add(new GarageAd(3, "Nuevo ático céntrico recién reformado.", this.pictures, 300,
                40, null));
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
                .filter(ad -> ad.getScore() >= 40)
                .filter(ad -> ad.getIrrelevantSince() == null)
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
        var expectedScore = 0;
        var updated = new ArrayList<Ad>();
        List<Ad> data = Arrays.asList(new FlatAd(1, "",
                Arrays.asList(), 10, null, new Date()));
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
    public void shouldAssignScoreForAllAds_HDPhoto() {
        // GIVEN
        var expectedScore = 20;
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        List<Ad> data = Arrays.asList(new FlatAd(1, "", Arrays.asList(), 10, null, new Date()));
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
    public void shouldAssignScoreForAllAds_SDPhoto() {
        // GIVEN
        var expectedScore = 10;
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", SD));
        List<Ad> data = Arrays.asList(new FlatAd(1, "",
                Arrays.asList(), 10, null, new Date()));
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
    public void shouldAssignScoreForAllAds_HDandSDPhoto() {
        // GIVEN
        var expectedScore = 30;
        var updated = new ArrayList<Ad>();
        var pictures = Arrays.asList(new Picture(1, "url", HD));
        List<Ad> data = Arrays.asList(new FlatAd(1, "",
                Arrays.asList(), 10, null, new Date()));
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
    public void shouldAssignScoreForAllAds_flat_DescriptionShort() {
        // GIVEN
        var expectedScore = 5;
        var updated = new ArrayList<Ad>();
        List<Ad> data = Arrays.asList(new FlatAd(1, "description",
                Arrays.asList(), 10, null, new Date()));
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
        List<Ad> data = Arrays.asList(new FlatAd(1, "Duis feugiat nibh non nulla mollis pellentesque. Nullam " +
                "quis " +
                "arcu augue. Suspendisse at dolor eget massa facilisis aliquet ut.",
                Arrays.asList(), 10, null, new Date()));
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
        List<Ad> data = Arrays.asList(new FlatAd(1, "Duis feugiat nibh non nulla mollis pellentesque. Nullam quis " +
                "arcu augue. Suspendisse at dolor eget massa facilisis aliquet eu ut.",
                Arrays.asList(), 10, null, new Date()));
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
        List<Ad> data = Arrays.asList(new FlatAd(1, "Nulla accumsan, elit vestibulum ultricies aliquet, mi mi " +
                "lacinia massa, nec vestibulum lacus turpis ac tortor. Fusce nulla nunc, malesuada in nisi in, cursus" +
                " auctor lectus.  Vestibulum laoreet purus felis, eu varius nulla efficitur sit amet. Donec auctor " +
                "ultrices ex, ac scelerisque  lorem malesuada in. Donec accumsan eget ipsum.",
                Arrays.asList(), 10, null, new Date()));
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
        List<Ad> data = Arrays.asList(new FlatAd(1, "Sed et vulputate est, non faucibus ligula. Phasellus tempus " +
                "pretium ligula, " +
                "non ullamcorper neque. Fusce tristique neque id tempus ornare. Nunc tincidunt libero id dolor " +
                "lobortis, at dictum neque hendrerit. Suspendisse sit amet bibendum dolor, non cursus orci. Duis " +
                "dapibus purus eget sem scelerisque, nec pellentesque ipsum consectetur. Mauris in.",
                Arrays.asList(), 10, null, new Date()));
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
        List<Ad> data = Arrays.asList(new FlatAd(1, "Aliquam vehicula enim eu turpis placerat venenatis. Ut vel " +
                "sapien sed sapien porta ultrices ac nec felis. Donec ac ultrices erat, a efficitur magna. Cras " +
                "posuere mollis ligula ut sodales. Fusce efficitur arcu nec nulla accumsan finibus. Aenean mi mi, " +
                "accumsan sit amet vulputate eu, maximus ac ante. Class aptent taciti sociosqu ad litora torquent per" +
                " conubia nostra, per inceptos himenaeos. Suspendisse non bibendum lacus. Etiam pulvinar ornare diam," +
                " in commodo dolor ultrices quis. Nunc ornare orci a pulvinar auctor.",
                Arrays.asList(), 10, 10, new Date()));
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "description",
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "Pellentesque sed eros non nisi tincidunt efficitur sed sit " +
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "Duis feugiat nibh non nulla mollis pellentesque. Nullam quis" +
                " " +
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "Nulla accumsan, elit vestibulum ultricies aliquet, mi mi " +
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "Sed et vulputate est, non faucibus ligula. Phasellus tempus " +
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "Aliquam vehicula enim eu turpis placerat venenatis. Ut vel " +
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "descripcion " + BRIGHT,
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "descripcion " + NEW,
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "descripcion " + DOWNTOWN,
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "descripcion " + REFORMED,
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "descripcion " + ATTIC,
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
        List<Ad> data = Arrays.asList(new FlatAd(1, "piso completo",
                pictures, 10, null, new Date()));
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
        List<Ad> data = Arrays.asList(new ChaletAd(1, "chalet completo",
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
        List<Ad> data = Arrays.asList(new GarageAd(1, "",
                pictures, 10, null, new Date()));
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

