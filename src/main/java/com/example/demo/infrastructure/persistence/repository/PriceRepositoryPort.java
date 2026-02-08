package com.example.demo.infrastructure.persistence.repository;

import com.example.demo.domain.model.FilterPrice;
import com.example.demo.infrastructure.persistence.entity.PriceEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepositoryPort extends JpaRepository<PriceEntity, Long> {
  @Query(
      """
          SELECT p FROM PriceEntity p
          WHERE p.brandId = :#{#filterPrice.brandId}
          AND p.productId = :#{#filterPrice.productId}
          AND :#{#filterPrice.dateToSearch} BETWEEN p.startDate AND p.endDate
          ORDER BY p.priority DESC
          LIMIT 1
      """)
  Optional<PriceEntity> findTopPrice(@Param("filterPrice") FilterPrice filterPrice);
}
