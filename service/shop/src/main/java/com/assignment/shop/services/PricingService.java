package com.assignment.shop.services;

import com.assignment.shop.dto.Price;

import java.util.List;

public interface PricingService {
    Price getPrice(String productCode, int pax);

    List<Price> getMultiplePricing(String productCode, int maxUnits);
}
