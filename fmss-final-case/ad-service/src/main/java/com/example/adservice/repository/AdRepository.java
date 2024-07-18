package com.example.adservice.repository;


import com.example.adservice.model.Ad;
import com.example.adservice.model.AdStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long>, JpaSpecificationExecutor<Ad> {
    int countByUserId(Long userId);

    List<Ad> findAllByUserId(Long userId);
    List<Ad> findAllByUserIdAndStatus(Long userId, AdStatus status);

}
