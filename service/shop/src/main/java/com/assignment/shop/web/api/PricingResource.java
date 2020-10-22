package com.assignment.shop.web.api;

import com.assignment.shop.dto.Price;
import com.assignment.shop.services.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/pricing")
public class PricingResource {


    private final PricingService pricingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Price getPricing(@RequestParam String productCode,
                            @RequestParam int units) {
        return pricingService.getPrice(productCode, units);
    }

    @GetMapping("/multiple")
    @ResponseStatus(HttpStatus.OK)
    public List<Price> getMultiplePricing(@RequestParam String productCode,
                                          @RequestParam int units) {
        return pricingService.getMultiplePricing(productCode, units);
    }




}
