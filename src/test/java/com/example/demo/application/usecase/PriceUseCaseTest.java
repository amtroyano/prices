package com.example.demo.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.demo.domain.exceptions.PriceNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

  @Mock private PriceRepositoryPort repositoryPort;

  @InjectMocks private PriceUseCase priceUseCase;

  @Test
  @DisplayName("Debe retornar un precio cuando existe en el repositorio")
  void shouldReturnPrice_WhenExists() {
    Price mockPrice = Price.builder().finalPrice(35.50).build();
    when(repositoryPort.findApplicablePrice(anyInt(), anyLong(), any()))
        .thenReturn(Optional.of(mockPrice));

    Price result = priceUseCase.execute(1, 35455L, LocalDateTime.now());

    assertNotNull(result);
    assertEquals(35.50, result.getFinalPrice());
    verify(repositoryPort, times(1)).findApplicablePrice(anyInt(), anyLong(), any());
  }

  @Test
  @DisplayName("Debe lanzar PriceNotFoundException cuando no hay precio")
  void shouldThrowException_WhenPriceNotFound() {
    when(repositoryPort.findApplicablePrice(anyInt(), anyLong(), any()))
        .thenReturn(Optional.empty());

    assertThrows(
        PriceNotFoundException.class, () -> priceUseCase.execute(1, 35455L, LocalDateTime.now()));
  }
}
