package com.example.demo.infrastructure.config;

import com.example.demo.infrastructure.annotation.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.example.demo",
    useDefaultFilters = true,
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class))
public class HexagonalConfig {}
