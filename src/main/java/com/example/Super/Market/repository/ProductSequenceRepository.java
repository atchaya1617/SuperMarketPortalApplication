package com.example.Super.Market.repository;

import com.example.Super.Market.entity.ProductSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSequenceRepository extends JpaRepository<ProductSequence,String> {
}
