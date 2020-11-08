package com.idealista.application.service;

import com.idealista.application.model.vo.QualityAdVo;

import java.util.List;

public interface AdService {

    /**
     * Gets all Ads for the quality team
     *
     * @return the list of quality add
     */
    List<QualityAdVo> findAllQualityAds();
}
