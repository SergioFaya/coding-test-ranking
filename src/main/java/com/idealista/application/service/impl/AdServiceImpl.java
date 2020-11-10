package com.idealista.application.service.impl;

import com.idealista.application.model.ad.Ad;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.application.service.AdVoCreator;
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
        return this.repository.findAll()
                .stream()
                .map(AdVoCreator::createQualityAd)
                .collect(toList());
    }

    @Override
    public List<PublicAdVo> findAllPublicAds() {
        return this.repository.findAll()
                .stream()
                .filter(this::isMinimumScore)
                .filter(this::isRelevant)
                .sorted(this::compareByScoreDesc)
                .map(AdVoCreator::createPublicAd)
                .collect(toList());
    }

    private int compareByScoreDesc(Ad o1, Ad o2) {
        return Integer.compare(o2.getScore(), o1.getScore());
    }

    private boolean isMinimumScore(Ad ad) {
        return ad.getScore() >= PUBLIC_QUALITY_SCORE_REQUIRED;
    }

    private boolean isRelevant(Ad ad) {
        return ad.getIrrelevantSince() == null;
    }

    /**
     * Computes the score field of the ads based on the content of each field
     */
    @Override
    public void assignScoreForAllAds() {
        var ads = this.repository.findAll();
        for (Ad ad : ads) {
            ad.computeScore();
            this.repository.save(ad);
        }
    }

}
