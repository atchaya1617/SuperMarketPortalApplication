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



//    @PostMapping
//    public ResponseEntity<PurchaseHeader> createPurchase(@Valid @RequestBody PurchaseHeader request) {
//        PurchaseHeader purchaseHeader = new PurchaseHeader();
//        purchaseHeader.setPrStatus(request.getPrStatus());
//        purchaseHeader.setAddress(request.getAddress());
//        purchaseHeader.setPhNo(request.getPhNo());
//        purchaseHeader.setSubTotal(request.getSubTotal());
//        purchaseHeader.setGrandTotal(request.getGrandTotal());
//        PurchaseHeader createdPurchaseHeader = purchaseService.createPurchase(purchaseHeader);
//
//        return ResponseEntity.ok(createdPurchaseHeader);
//    }

//
//    @PostMapping("/{prNo}/addItems")
//    public ResponseEntity<PurchaseHeaderDto> addItems(@PathVariable("prNo") String prNo, @RequestBody List<PipelineDto> pipelineDtos) {
//        purchaseService.addItemsToPurchase(prNo, pipelineDtos);
//        PurchaseHeader updatedPurchaseHeader = purchaseService.getPurchaseHeaderByPrNo(prNo);
//        PurchaseHeaderDto purchaseHeaderDto = new PurchaseHeaderDto(updatedPurchaseHeader);
//        return new ResponseEntity<>(purchaseHeaderDto, HttpStatus.OK);
//    }

//    @PutMapping
//    public ResponseEntity<String> update(@RequestBody PurchaseHeader purchaseHeader) {
//        purchaseService.updateItem(purchaseHeader);
//        return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
//    }

//    @GetMapping("/getById/{prNo}")
//    public ResponseEntity<PurchaseHeaderDto> get(@PathVariable("prNo") String prNo){
//            PurchaseHeader purchaseHeader = purchaseService.getPurchaseHeaderByPrNo(prNo);
//            if (purchaseHeader == null) {
//                return ResponseEntity.notFound().build();
//            }
//            PurchaseHeaderDto purchaseHeaderDto = new PurchaseHeaderDto(purchaseHeader);
//            return ResponseEntity.ok().body(purchaseHeaderDto);
//    }
}
