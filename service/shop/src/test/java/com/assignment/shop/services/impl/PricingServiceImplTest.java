package com.assignment.shop.services.impl;

import com.assignment.shop.domain.PriceAdjustment;
import com.assignment.shop.domain.Product;
import com.assignment.shop.dto.Price;
import com.assignment.shop.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PricingServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PricingServiceImpl pricingService;

    @Test
    public void shouldSuccessOnValidCartoonPrice() {

        given(productRepository.findByProductCode("penguinear")).willReturn(getProduct());

        Price pricing = pricingService.getPrice("penguinear", 20);
        assertEquals(175, pricing.getTotalPrice());
    }

    private Optional<Product> getProduct() {

        PriceAdjustment priceAdjustment = new PriceAdjustment();
        priceAdjustment.setDiscountPercentage(10);
        priceAdjustment.setMinCartoonsDiscount(3);
        priceAdjustment.setPerUnitIncreasePercentage(30);

        Product penguinEar = new Product();
        penguinEar.setProductCode("penguinear");
        penguinEar.setTitle("Penguin Ears");
        penguinEar.setCartoonPrice(175);
        penguinEar.setUnitsPerCartoon(20);
        penguinEar.setPriceAdjustment(priceAdjustment);

        return Optional.of(penguinEar);

    }


}