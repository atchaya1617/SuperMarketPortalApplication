package com.example.Super.Market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sequence_table")
public class PurchaseSequence {

    @Id
    @Column(name = "sequence_name")
    private String sequenceName;
    @Column(name = "sequence_value")
    private Long sequenceValue;
}
