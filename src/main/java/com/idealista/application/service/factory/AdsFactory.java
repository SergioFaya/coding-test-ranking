package com.idealista.application.service.factory;

import com.idealista.application.model.Picture;
import com.idealista.application.model.ad.Ad;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class AdsFactory {

    private static void fillCommonFields(PublicAdVo vo, Ad ad) {
        vo.setId(ad.getId());
        //vo.setTypology(ad.getTypology().name());
        vo.setDescription(ad.getDescription());
        vo.setHouseSize(ad.getSize());
        //vo.setGardenSize(ad.getGardenSize());
        if (ad.getPictures() != null) {
            vo.setPictureUrls(ad.getPictures()
                    .stream()
                    .map(Picture::getUrl)
                    .collect(toList()));
        } else {
            vo.setPictureUrls(new ArrayList<>());
        }
    }

    public static PublicAdVo createPublicAdVo(Ad ad) {
        var publicAd = new PublicAdVo();
        fillCommonFields(publicAd, ad);
        return publicAd;
    }

    public static QualityAdVo createQualityAdVo(Ad ad) {
        var qualityAd = new QualityAdVo();
        fillCommonFields(qualityAd, ad);
        qualityAd.setScore(ad.getScore());
        qualityAd.setIrrelevantSince(ad.getIrrelevantSince());
        return qualityAd;
    }

}
