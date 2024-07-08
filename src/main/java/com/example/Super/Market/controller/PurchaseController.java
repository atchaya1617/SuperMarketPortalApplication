package com.example.Super.Market.controller;

import com.example.Super.Market.entity.PurchaseHeader;
import com.example.Super.Market.service.ProductService;
import com.example.Super.Market.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
        public ResponseEntity<PurchaseHeader> createPurchase(@RequestBody PurchaseHeader purchaseRequest) {
            PurchaseHeader createdPurchaseHeader = purchaseService.createPurchase(purchaseRequest);
            return new ResponseEntity<>(createdPurchaseHeader, HttpStatus.CREATED);
        }

    @PutMapping("/{prNo}")
    public ResponseEntity<PurchaseHeader> updatePurchase( @RequestBody PurchaseHeader purchaseHeader) {
        PurchaseHeader updatedPurchaseHeader = purchaseService.updatePurchase( purchaseHeader);
        return new ResponseEntity<>(updatedPurchaseHeader,HttpStatus.OK);
    }


//    @GetMapping("/getById/{prNo}")
//    public ResponseEntity<PurchaseHeader> get(@PathVariable("prNo") String prNo){
//            PurchaseHeader purchaseHeader = purchaseService.getPurchaseHeaderByPrNo(prNo);
//            if (purchaseHeader == null) {
//                return ResponseEntity.notFound().build();
//            }
//            PurchaseHeaderDto purchaseHeader = new PurchaseHeader(purchaseHeader);
//            return ResponseEntity.ok().body(purchaseHeader);
//    }
}
