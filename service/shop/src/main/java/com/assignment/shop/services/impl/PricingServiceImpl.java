package com.assignment.shop.services.impl;

import com.assignment.shop.domain.Product;
import com.assignment.shop.dto.Price;
import com.assignment.shop.repositories.ProductRepository;
import com.assignment.shop.services.PricingService;
import com.assignment.shop.web.exceptions.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PricingServiceImpl implements PricingService {


    private final ProductRepository productRepository;

    @Override
    public Price getPrice(String productCode, int units) {


        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new InvalidDataException("Invalid product code"));
        Price price = new Price();

        int cartoons = units / product.getUnitsPerCartoon();
        int singleUnits = units % product.getUnitsPerCartoon();

        price.setCartoons(cartoons);
        price.setSingleUnits(singleUnits);
        price.setTotalUnits(units);
        price.setTotalPrice(getPriceAdjustment(cartoons, singleUnits, product));


        return price;
    }

    @Override
    public List<Price> getMultiplePricing(String productCode, int maxUnits) {
        List<Price> priceList = new ArrayList<>();
        for (int units = 1; units <= maxUnits; units++) {
            priceList.add(getPrice(productCode,units));
        }
        return priceList;
    }

    private double getPriceAdjustment(int cartoons, int singles, Product product) {
        double totalPrice = 0;

        if (cartoons > 0) {
            totalPrice += cartoons * getCartoonPrice(cartoons , product);
        }

        if (singles > 0) {
            totalPrice += singles * getIncreasedUnitPrice(product.getCartoonPrice(),
                    product.getPriceAdjustment().getPerUnitIncreasePercentage(), product.getUnitsPerCartoon());

        }
        return totalPrice;
    }

    private double getCartoonPrice(int cartoons, Product product) {
        if (cartoons >= 3) {
            return  getDiscountedCartoonPrice(product.getCartoonPrice(),
                    product.getPriceAdjustment().getDiscountPercentage());
        } else {
            return product.getCartoonPrice();
        }
    }


    private double getDiscountedCartoonPrice(double cartoonPrice, double discountPercentage) {
        return cartoonPrice * ((100 - discountPercentage) / 100);
    }

    private double getIncreasedUnitPrice(double cartoonPrice, double increase, int unitsPerCartoon) {
        return (cartoonPrice * ((100 + increase) / 100)) / unitsPerCartoon;
    }


}
