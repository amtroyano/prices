package com.example.demo.infrastructure.adapter.outbound.repository;

import com.example.demo.infrastructure.adapter.outbound.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
  @Query(
      "SELECT p FROM PriceEntity p WHERE p.brandId = :brandId AND p.productId = :productId "
          + "AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
  Optional<PriceEntity> findTopPrice(Integer brandId, Long productId, LocalDateTime date);
}
