package com.example.demo.infrastructure.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
    packages = "com.example.demo",
    importOptions = {ImportOption.DoNotIncludeTests.class})
public class HexagonalArchitectureTest {

  @ArchTest
  static final ArchRule hexagonal_architecture_layers =
      onionArchitecture()
          .withOptionalLayers(true)
          .domainModels("..domain.model..", "..domain.exceptions..")
          .domainServices("..domain.service..", "..domain.port..")
          .applicationServices("..application..")
          .adapter(
              "persistence",
              "..infrastructure.persistence..",
              "..infrastructure.adapter.persistence..")
          .adapter(
              "rest",
              "..infrastructure.adapter.inbound..",
              "..infrastructure.port..",
              "..infrastructure.adapter.handler..")
          .adapter("config", "..infrastructure.config..", "..infrastructure.aop..");

  @ArchTest
  static final ArchRule domain_should_be_pure_java =
      noClasses()
          .that()
          .resideInAPackage("..domain..")
          .should()
          .dependOnClassesThat()
          .resideInAnyPackage(
              "org.springframework..",
              "org.mapstruct..",
              "jakarta.persistence..",
              "com.fasterxml.jackson..",
              "org.hibernate..")
          .because("El Dominio debe ser agnóstico a cualquier librería o framework");

  @ArchTest
  static final ArchRule application_should_be_independent =
      classes()
          .that()
          .resideInAPackage("..application..")
          .should()
          .onlyDependOnClassesThat()
          .resideInAnyPackage(
              "java..",
              "com.example.demo.application..",
              "com.example.demo.domain..",
              "org.mapstruct..",
              "org.slf4j..",
              "lombok..")
          .because(
              "La capa de Aplicación solo puede depender del Dominio y de MapStruct para mapeo");

  @ArchTest
  static final ArchRule application_should_not_use_spring =
      noClasses()
          .that()
          .resideInAPackage("..application..")
          .should()
          .dependOnClassesThat()
          .resideInAPackage("org.springframework..")
          .because(
              "Los Casos de Uso no deben usar anotaciones de Spring (usa configuración manual en Infra)");

  @ArchTest
  static final ArchRule ports_should_be_interfaces =
      classes()
          .that()
          .resideInAPackage("..domain.port..")
          .should()
          .beInterfaces()
          .because("En Hexagonal, los puertos (entrada/salida) deben ser interfaces (contratos)");
}
