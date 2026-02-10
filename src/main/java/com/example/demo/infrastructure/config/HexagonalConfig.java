package com.example.demo.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.example.demo.application",
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*UseCase"))
public class HexagonalConfig {}
