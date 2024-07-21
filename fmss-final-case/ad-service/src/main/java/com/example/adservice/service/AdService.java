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
import com.example.adservice.producer.ad.AdActivationDto;
import com.example.adservice.producer.ad.AdActivationProducer;
import com.example.adservice.producer.log.LogProducer;
import com.example.adservice.producer.log.LogRequest;
import com.example.adservice.repository.AdRepository;
import com.example.adservice.repository.spesification.AdSpesification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdService {
    private final AdRepository adRepository;

    private final LogProducer logProducer;
    private final AdConverter adConverter;

    private final AdActivationProducer adActivationProducer;
    private final SubscriptionService subscriptionService;

    public <T> void save(T c ,Long userId){
        log.info("Request to save real estate ad");
        logProducer.sendLog(new LogRequest("[ad-service]",
                Level.INFO,
                "adservice",
                "Request to save real estate ad: "+userId,
                new Date()));
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
        if (adRepository.countByUserId(ad.getUserId())>=subscriptionService.findCurrentSubscription(ad.getUserId()).getAdLimit()){
        throw new ExhaustedAdLimitException("Ad posting limit has been exhausted.");
        }

        Ad ad_ = adRepository.save(ad);

        AdActivationDto dto = new AdActivationDto(ad_.getId(),userId);
        //Directs the ad to the activation service
        adActivationProducer.sendAdActivation(dto);
    }

    public SearchResponse searchActive(AdSearchRequest request){

        log.info("Request to search active real estate ad by:{}",request);
        logProducer.sendLog(new LogRequest("[ad-service]",
                Level.INFO,
                "adservice",
                "Request to search active real estate ad by:{}"+request,
                new Date()));

        Specification<Ad> adSpecification = AdSpesification.initRealEstateAdSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(request.getSort(), "amount"));


        Page<Ad> ads = adRepository.findAll(adSpecification, pageRequest);
        return SearchResponse.builder()
                .response(adConverter.toResponse(ads.stream().toList()))
                .totalPageNumber(ads.getTotalPages())
                .build();

    }

    public void updateStatus(AdStatus status, Long id,Long userId){
        log.info("Request to update status as {} by id:{}",status,id);
        logProducer.sendLog(new LogRequest("[ad-service]",
                Level.INFO,
                "adservice",
                "Request to update status as "+status+" by id:"+id,
                new Date()));

        Ad ad = adRepository.findByIdAndUserId(id,userId)
                .orElseThrow(()->new AdNotFoundException("Real estate ad not found by id:"+id));

        ad.setStatus(status);

        adRepository.save(ad);
    }

    public AdResponse findById(Long id){
        log.info("Request to find by id {}",id);
        logProducer.sendLog(new LogRequest("[ad-service]",
                Level.INFO,
                "adservice",
                "Request to find by id {}"+id,
                new Date()));
        Ad ad = adRepository.findById(id).orElseThrow(()->new AdNotFoundException("Real estate ad not found by id:"+id));
        return adConverter.toResponse(ad) ;
    }

    public List<AdResponse> findAllByUserIdByStatus(Long userId, Optional<AdStatus> status){
        log.info("Request to find all by id {} with status: {}",userId,status);
        logProducer.sendLog(new LogRequest("[ad-service]",
                Level.INFO,
                "adservice",
                "Request to find all by id "+userId+" with status::"+status,
                new Date()));

        if (status.isPresent()){
            return adConverter.toResponse(adRepository.findAllByUserIdAndStatus(userId,status.get())) ;
        }

        return adConverter.toResponse(adRepository.findAllByUserId(userId)) ;
    }


    public void updateAllStatusByUser(AdStatus status, Long id){
        log.info("Request to all update status as {} by id:{}",status,id);
        logProducer.sendLog(new LogRequest("[ad-service]",
                Level.INFO,
                "adservice",
                "Request to all update status as "+status+" by id:"+id,
                new Date()));

        List<Ad> ads = adRepository.findAllByUserId(id);

        ads = ads.stream()
                .filter(it->it.getStatus().equals(AdStatus.ACTIVE))
                .peek(it->it.setStatus(AdStatus.PASSIVE))
                .toList();

        adRepository.saveAll(ads);

    }

    
}
