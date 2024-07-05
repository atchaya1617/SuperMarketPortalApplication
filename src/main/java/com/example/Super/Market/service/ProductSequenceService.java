package com.example.Super.Market.service;

import com.example.Super.Market.entity.ProductSequence;
import com.example.Super.Market.repository.ProductSequenceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSequenceService {

    @Autowired
    private ProductSequenceRepository sequenceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public synchronized Long getNextSequenceValue(String sequenceName) {
        ProductSequence sequence = entityManager.find(ProductSequence.class, sequenceName, LockModeType.PESSIMISTIC_WRITE);

        if (sequence == null) {
            sequence = new ProductSequence();
            sequence.setSequenceName(sequenceName);
            sequence.setSequenceValue(1L);
            entityManager.persist(sequence);
            entityManager.flush();
            return 1L;
        } else {
            Long nextValue = sequence.getSequenceValue();
            sequence.setSequenceValue(nextValue + 1);
            entityManager.merge(sequence);
            entityManager.flush();
            return nextValue + 1;
        }
    }

}
