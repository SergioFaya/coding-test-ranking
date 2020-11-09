package com.idealista.application.service.impl;

import com.idealista.application.model.vo.PublicAdVo;
import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.factory.AdsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository repository;

    public AdServiceImpl() { }

    public AdServiceImpl(AdRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<QualityAdVo> findAllQualityAds() {
        var ads = repository.findAll()
                .stream()
                .map(ad -> AdsFactory.createQualityAdVo(ad))
                .collect(toList());
        return ads;
    }

    @Override
    public List<PublicAdVo> findAllPublicAds() {
        var ads = repository.findAll()
                .stream()
                .sorted((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()))
                .map(ad -> AdsFactory.createPublicAdVo(ad))
                .collect(toList());
        return ads;
    }

}
