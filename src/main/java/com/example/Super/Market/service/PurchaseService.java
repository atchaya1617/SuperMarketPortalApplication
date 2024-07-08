package com.example.Super.Market.service;
import com.example.Super.Market.entity.PurchaseHeaderLine;
import com.example.Super.Market.entity.PurchaseHeader;
import com.example.Super.Market.repository.PurchaseHeaderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    PurchaseSequenceService sequenceService;

    @Autowired
    PurchaseHeaderRepository purchaseHeaderRepository;


    public PurchaseHeader createPurchase(PurchaseHeader purchaseHeader) {
        String purchaseNo = String.valueOf(sequenceService.getNextSequenceValue("purchase_sequence"));
        purchaseHeader.setPrNo("PR-" + purchaseNo);

        double grandTotal = 0.0;
        double subTotal = 0.0;

        for (PurchaseHeaderLine purchaseHeaderLine : purchaseHeader.getLine()) {
            purchaseHeaderLine.setPurchaseNo(purchaseHeader.getPrNo());

            double pipelineSubTotal = purchaseHeaderLine.getQuantity() * purchaseHeaderLine.getProductPrice();
            purchaseHeaderLine.setSubTotal(pipelineSubTotal);

            double pipelineGrandTotal = pipelineSubTotal + pipelineSubTotal * (purchaseHeaderLine.getTax() / 100) - purchaseHeaderLine.getDiscount();
            purchaseHeaderLine.setGrandTotal(pipelineGrandTotal);

            grandTotal += pipelineGrandTotal;
            subTotal += pipelineSubTotal;
        }

        purchaseHeader.setSubTotal(subTotal);
        purchaseHeader.setGrandTotal(grandTotal);

        PurchaseHeader savedPurchaseHeader = purchaseHeaderRepository.save(purchaseHeader);
        return savedPurchaseHeader;
    }

    public PurchaseHeader updatePurchase(PurchaseHeader updatedPurchaseHeader) {
        PurchaseHeader existingPurchaseHeader = purchaseHeaderRepository.findById(updatedPurchaseHeader.getPrNo()).orElse(null);

        if (existingPurchaseHeader == null) {

            throw new EntityNotFoundException("PurchaseHeader not found for ID: " + updatedPurchaseHeader.getPrNo());
        }

        existingPurchaseHeader.setLine(updatedPurchaseHeader.getLine());

        double grandTotal = existingPurchaseHeader.getGrandTotal();
        double subTotal = existingPurchaseHeader.getSubTotal();

        for (PurchaseHeaderLine line : existingPurchaseHeader.getLine()) {
            line.setPurchaseNo(existingPurchaseHeader.getPrNo());

            double lineSubTotal = line.getQuantity() * line.getProductPrice();
            line.setSubTotal(lineSubTotal);

            double lineGrandTotal = lineSubTotal + (lineSubTotal * (line.getTax() / 100)) - line.getDiscount();
            line.setGrandTotal(lineGrandTotal);

            grandTotal += lineGrandTotal;
            subTotal += lineSubTotal;
        }

        existingPurchaseHeader.setSubTotal(subTotal);
        existingPurchaseHeader.setGrandTotal(grandTotal);

        return purchaseHeaderRepository.saveAndFlush(existingPurchaseHeader);
    }

    public PurchaseHeader getProductByPrNo(String prNo) {
        Optional<PurchaseHeader> purchaseHeaderOptional = purchaseHeaderRepository.findByPrNo(prNo);
        return purchaseHeaderOptional.orElse(null);
    }
}