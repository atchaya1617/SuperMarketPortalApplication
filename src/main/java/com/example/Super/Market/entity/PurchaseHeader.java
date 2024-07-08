package com.example.Super.Market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "purchase_header")
@Getter
@Setter
public class PurchaseHeader {

    @Id
    @SequenceGenerator(name = "purchase_sequence", sequenceName = "purchase_sequence", allocationSize = 1)
    @Column(name = "purchase_no")
    private String prNo;

    @Column(name = "purchase_status")
    private String prStatus;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_no")
    private long phNo;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "grand_total")
    private double grandTotal;

    @OneToMany(mappedBy = "purchaseNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseHeaderLine> line ;

}
