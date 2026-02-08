// package com.example.demo.infrastructure.config;
//
// import javax.sql.DataSource;
// import liquibase.integration.spring.SpringLiquibase;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// @Configuration
// public class LiquibaseConfig {
//
//  @Bean
//  public SpringLiquibase liquibase(DataSource dataSource) {
//    System.out.println("🚀 EJECUTANDO LIQUIBASE MANUALMENTE...");
//    SpringLiquibase liquibase = new SpringLiquibase();
//    liquibase.setDataSource(dataSource);
//    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
//    // Esto asegura que falle si el archivo no existe
//    liquibase.setShouldRun(true);
//    return liquibase;
//  }
// }
