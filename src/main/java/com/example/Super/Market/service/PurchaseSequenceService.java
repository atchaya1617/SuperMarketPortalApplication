package com.example.Super.Market.service;

import com.example.Super.Market.entity.PurchaseSequence;
import com.example.Super.Market.repository.PurchaseSequenceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PurchaseSequenceService {

    @Autowired
    private PurchaseSequenceRepository sequenceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public synchronized Long getNextSequenceValue(String sequenceName) {
        PurchaseSequence sequence = entityManager.find(PurchaseSequence.class, sequenceName, LockModeType.PESSIMISTIC_WRITE);

        if (sequence == null) {
            sequence = new PurchaseSequence();
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
