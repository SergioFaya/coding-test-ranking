package com.idealista.application.service.factory;

import com.idealista.application.model.Picture;
import com.idealista.application.model.ad.Ad;
import com.idealista.application.model.ad.ChaletAd;
import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.model.vo.QualityAdVo;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Factory for creating the view objects related to Ads
 */
public class AdsFactory {

    private AdsFactory() {
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

    /**
     * Fills in common parameters for every type of VO
     *
     * @param vo
     * @param ad
     */
    private static void fillCommonFields(PublicAdVo vo, Ad ad) {
        vo.setId(ad.getId());
        vo.setTypology(ad.getTypology().name());
        vo.setDescription(ad.getDescription());
        vo.setHouseSize(ad.getSize());
        if (ad.getPictures() != null) {
            vo.setPictureUrls(ad.getPictures()
                    .stream()
                    .map(Picture::getUrl)
                    .collect(toList()));
        } else {
            vo.setPictureUrls(new ArrayList<>());
        }

        fillGardenSizeForChalet(vo, ad);
    }

    /**
     * Checks that the {@link Ad} instance is a {@link ChaletAd} to populate the garden size field
     *
     * @param vo
     * @param ad
     */
    private static void fillGardenSizeForChalet(PublicAdVo vo, Ad ad) {
        if (ad instanceof ChaletAd) {
            var chaletAd = (ChaletAd) ad;
            vo.setGardenSize(chaletAd.getGardenSize());
        }
    }

}
