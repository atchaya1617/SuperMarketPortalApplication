package com.example.Super.Market.repository;

import com.example.Super.Market.entity.PurchaseHeader;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PurchaseHeaderRepository extends JpaRepository<PurchaseHeader, String> {
    Optional<PurchaseHeader> findByPrNo(String prNo);
}
