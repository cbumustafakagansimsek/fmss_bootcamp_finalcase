package com.example.adservice.service;


import com.example.adservice.client.subscription.service.SubscriptionService;
import com.example.adservice.converter.AdConverter;
import com.example.adservice.dto.request.DetachedHouseRequest;
import com.example.adservice.dto.request.FlatRequest;
import com.example.adservice.dto.request.AdSearchRequest;
import com.example.adservice.dto.request.VillaRequest;
import com.example.adservice.dto.response.AdResponse;
import com.example.adservice.dto.response.SearchResponse;
import com.example.adservice.exception.AdNotFoundException;
import com.example.adservice.exception.ExhaustedAdLimitException;
import com.example.adservice.exception.HouseTypeIsInvalidException;
import com.example.adservice.factory.impl.DetachedHouseFactory;
import com.example.adservice.factory.impl.FlatFactory;
import com.example.adservice.factory.impl.VillaFactory;
import com.example.adservice.model.Ad;
import com.example.adservice.model.AdStatus;
import com.example.adservice.producer.AdActivationProducer;
import com.example.adservice.repository.AdRepository;
import com.example.adservice.repository.spesification.AdSpesification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdService {
    private final AdRepository AdRepository;

    private final AdConverter adConverter;

    private final AdActivationProducer adActivationProducer;
    private final SubscriptionService subscriptionService;

    public <T> void save(T c ,Long userId){
        log.info("Request to save real estate ad");
        Ad ad;
        if (c.getClass() == FlatRequest.class ){
            ad = new FlatFactory().createAd((FlatRequest) c,userId);
        } else if (c.getClass() == VillaRequest.class) {
            ad = new VillaFactory().createAd((VillaRequest) c,userId);
        }else if (c.getClass() == DetachedHouseRequest.class) {
            ad = new DetachedHouseFactory().createAd((DetachedHouseRequest) c,userId);
        }else {
            throw new HouseTypeIsInvalidException("Request class is invalid ");
        }

        //If 10 usage rights have expired
        if (AdRepository.countByUserId(ad.getUserId())>=subscriptionService.findCurrentSubscription(ad.getUserId()).getAdLimit()){
        throw new ExhaustedAdLimitException("Ad posting limit has been exhausted.");
        }

        Ad ad_ = AdRepository.save(ad);

        //Directs the ad to the activation service
        adActivationProducer.sendAdActivation(ad_.getId());
    }

    public SearchResponse searchAll(AdSearchRequest request){

        log.info("Request to search all real estate ad by:{}",request);

        Specification<Ad> adSpecification = AdSpesification.initRealEstateAdSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(request.getSort(), "amount"));


        Page<Ad> ads = AdRepository.findAll(adSpecification, pageRequest);
        return SearchResponse.builder()
                .response(adConverter.toResponse(ads.stream().toList()))
                .totalPageNumber(ads.getTotalPages())
                .build();

    }

    public void updateStatus(AdStatus status, Long id){
        log.info("Request to update status as {} by id:{}",status,id);

        Ad ad = AdRepository.findById(id)
                .orElseThrow(()->new AdNotFoundException("Real estate ad not found by id:"+id));

        ad.setStatus(status);

        AdRepository.save(ad);
    }

    public AdResponse findById(Long id){
        log.info("Request to find by id {}",id);
        Ad ad = AdRepository.findById(id).orElseThrow(()->new AdNotFoundException("Real estate ad not found by id:"+id));
        return adConverter.toResponse(ad) ;
    }

    public List<AdResponse> findAllByUserId(Long userId, Optional<AdStatus> status){
        log.info("Request to find all by id {} with status: {}",userId,status);
        if (status.isPresent()){
            return adConverter.toResponse(AdRepository.findAllByUserIdAndStatus(userId,status.get())) ;
        }

        return adConverter.toResponse(AdRepository.findAllByUserId(userId)) ;
    }

    
}
