package com.example.Super.Market.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "product_sequence")
public class ProductSequence {

    @Id
    @Column(name = "sequence_name")
    private String sequenceName;
    @Column(name = "sequence_value")
    private Long sequenceValue;
}

