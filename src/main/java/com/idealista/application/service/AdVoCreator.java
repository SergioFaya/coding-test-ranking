package com.idealista.application.service;

import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;

/**
 * Defines the methods for creating Vos of {@link com.idealista.application.model.ad.Ad}
 */
public interface AdVoCreator {

    /**
     * Returns a {@link QualityAdVo} for the Ad
     *
     * @return
     */
    QualityAdVo createQualityAd();

    /**
     * Returns a {@link PublicAdVo} for the Ad
     *
     * @return
     */
    PublicAdVo createPublicAd();
}
