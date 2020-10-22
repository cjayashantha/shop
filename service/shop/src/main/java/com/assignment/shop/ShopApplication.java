package com.assignment.shop;

import com.assignment.shop.domain.PriceAdjustment;
import com.assignment.shop.domain.Product;
import com.assignment.shop.repositories.PriceAdjustmentRepository;
import com.assignment.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class ShopApplication implements CommandLineRunner {


	private final ProductRepository productRepository;

	private final PriceAdjustmentRepository priceAdjustmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		if (this.productRepository.findAll().isEmpty()) {
			log.info("Inserting initial data");

			PriceAdjustment priceAdjustment = new PriceAdjustment();
			priceAdjustment.setDiscountPercentage(10);
			priceAdjustment.setMinCartoonsDiscount(3);
			priceAdjustment.setPerUnitIncreasePercentage(30);

			priceAdjustment = priceAdjustmentRepository.save(priceAdjustment);

			Product penguinEar = new Product();
			penguinEar.setProductCode("penguinear");
			penguinEar.setTitle("Penguin Ears");
			penguinEar.setCartoonPrice(175);
			penguinEar.setUnitsPerCartoon(20);
			penguinEar.setPriceAdjustment(priceAdjustment);

			Product horseShoe = new Product();
			horseShoe.setProductCode("horseshoe");
			horseShoe.setTitle("HorseShoe");
			horseShoe.setCartoonPrice(875);
			horseShoe.setUnitsPerCartoon(5);
			horseShoe.setPriceAdjustment(priceAdjustment);

			this.productRepository.saveAll(Arrays.asList(penguinEar,horseShoe));

			log.info("Inserted initial data");
		}
	}


}
