package com.assignment.shop.repositories;

import com.assignment.shop.domain.PriceAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceAdjustmentRepository extends JpaRepository<PriceAdjustment, Long> {

}
