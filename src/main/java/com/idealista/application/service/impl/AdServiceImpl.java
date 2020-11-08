package com.idealista.application.service.impl;

import com.idealista.application.repository.AdRepository;
import com.idealista.application.service.AdService;
import com.idealista.application.model.vo.QualityAdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository repository;

    public AdServiceImpl(){}

    public AdServiceImpl(AdRepository repository){ this.repository = repository; }

    @Override
    public List<QualityAdVo> findAllQualityAds() {
        var ads = repository.findAll()
                .stream().map( ad -> new QualityAdVo(ad)).collect(toList());

        return ads;
    }

}
