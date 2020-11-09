package com.idealista.application.service;

import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;

import java.util.List;

public interface AdService {

    /**
     * Gets all Ads for the quality team
     *
     * @return the list of {@link QualityAdVo}
     */
    List<QualityAdVo> findAllQualityAds();

    /**
     * Gets all Ads for the public listing on the website
     *
     * @return the list of {@link PublicAdVo}
     */
    List<PublicAdVo> findAllPublicAds();

    /**
     * Computes the score of the ads in the db.
     */
    void assignScoreForAllAds();
}
