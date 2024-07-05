package com.example.Super.Market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pipeline")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pipeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "tax")
    private double tax;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "grand_total")
    private double grandTotal;

    @Column(name = "discount")
    private double discount;

    @Column(name = "purchase_no", nullable = false)
    private String purchaseNo;

    @Column(name = "product_id", updatable = false)
    private String productId;
}