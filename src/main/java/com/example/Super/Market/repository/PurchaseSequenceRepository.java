package com.example.Super.Market.repository;

import com.example.Super.Market.entity.PurchaseSequence;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PurchaseSequenceRepository extends JpaRepository<PurchaseSequence, String> {
}
