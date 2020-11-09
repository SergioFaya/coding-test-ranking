package com.idealista.application.service.impl;

import com.idealista.application.model.ad.Ad;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.application.service.factory.AdsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AdServiceImpl implements AdService {

    private static final int PUBLIC_QUALITY_SCORE_REQUIRED = 40;

    @Autowired
    private AdRepository repository;

    public AdServiceImpl() {
    }

    public AdServiceImpl(AdRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<QualityAdVo> findAllQualityAds() {
        var ads = this.repository.findAll()
                .stream()
                .map(AdsFactory::createQualityAdVo)
                .collect(toList());
        return ads;
    }

    @Override
    public List<PublicAdVo> findAllPublicAds() {
        var ads = this.repository.findAll()
                .stream()
                .filter(ad -> ad.getScore() >= PUBLIC_QUALITY_SCORE_REQUIRED)
                .filter(this::isRelevant)
                .sorted((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()))
                .map(AdsFactory::createPublicAdVo)
                .collect(toList());
        return ads;
    }

    private boolean isRelevant(Ad ad) {
        return ad.getIrrelevantSince() == null;
    }

    /**
     * Computes the score field of the ads based on the content of each field
     */
    @Override
    public void assignScoreForAllAds() {
        this.repository.findAll()
                .stream()
                .forEach(ad -> {
                    ad.setScore(ad.computeScore());
                    this.repository.save(ad);
                });
    }


}
