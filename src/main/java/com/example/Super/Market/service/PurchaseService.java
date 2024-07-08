package com.example.Super.Market.service;
import com.example.Super.Market.entity.PurchaseHeaderLine;
import com.example.Super.Market.entity.PurchaseHeader;
import com.example.Super.Market.repository.PHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    PurchaseSequenceService sequenceService;

    @Autowired
    PHRepository phRepository;


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

        PurchaseHeader savedPurchaseHeader = phRepository.save(purchaseHeader);
        return savedPurchaseHeader;
    }

    public PurchaseHeader updatePurchase( PurchaseHeader updatedPurchaseHeader) {
        PurchaseHeader existingPurchaseHeader = phRepository.findById(updatedPurchaseHeader.getPrNo()).orElse(null);

        existingPurchaseHeader.setLine(updatedPurchaseHeader.getLine());

        double grandTotal = 0.0;
        double subTotal = 0.0;

        for (PurchaseHeaderLine purchaseHeaderLine : existingPurchaseHeader.getLine()) {
            purchaseHeaderLine.setPurchaseNo(existingPurchaseHeader.getPrNo());

            double pipelineSubTotal = purchaseHeaderLine.getQuantity() * purchaseHeaderLine.getProductPrice();
            purchaseHeaderLine.setSubTotal(pipelineSubTotal);

            double pipelineGrandTotal = pipelineSubTotal + pipelineSubTotal * (purchaseHeaderLine.getTax() / 100) - purchaseHeaderLine.getDiscount();
            purchaseHeaderLine.setGrandTotal(pipelineGrandTotal);

            grandTotal += pipelineGrandTotal;
            subTotal += pipelineSubTotal;
        }

        existingPurchaseHeader.setSubTotal(subTotal);
        existingPurchaseHeader.setGrandTotal(grandTotal);

        return phRepository.saveAndFlush(existingPurchaseHeader);
    }
}