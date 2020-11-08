package com.idealista.infrastructure.api;

import java.util.ArrayList;
import java.util.List;


import com.idealista.application.model.vo.QualityAdVo;
import com.idealista.application.service.AdService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ads")
public class AdsController implements InitializingBean {

    @Autowired
    private AdService adService;

    @GetMapping(value = "/quality")
    public ResponseEntity<List<QualityAdVo>> qualityListing() {
        try {
            List<QualityAdVo> ads = new ArrayList<>();
            ads.addAll(adService.findAllQualityAds());
            return ResponseEntity.accepted().body(ads);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //TODO añade url del endpoint
    public ResponseEntity<List<PublicAd>> publicListing() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    //TODO añade url del endpoint
    public ResponseEntity<Void> calculateScore() {
        //TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(adService, "qualityAdService must be injected");
    }
}
