package com.assignment.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String productCode;

    private String title;

    private int unitsPerCartoon;

    private double cartoonPrice;

    @OneToOne
    private PriceAdjustment priceAdjustment;



}
