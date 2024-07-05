package com.example.Super.Market.service;
import com.example.Super.Market.entity.Pipeline;
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
        for (Pipeline pipeline : purchaseHeader.getLine()) {
            pipeline.setPurchaseNo(purchaseHeader.getPrNo());
             double subTotal = pipeline.getQuantity() * pipeline.getProductPrice();
            pipeline.setSubTotal(subTotal);
            grandTotal += subTotal +  pipeline.getSubTotal() * (pipeline.getTax() / 100) - pipeline.getDiscount();
            pipeline.setGrandTotal(grandTotal);
            purchaseHeader.setSubTotal(subTotal);
        }
        purchaseHeader.setGrandTotal(grandTotal);
        PurchaseHeader savedPurchaseHeader = phRepository.save(purchaseHeader);
        return savedPurchaseHeader;
    }

}