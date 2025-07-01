package com.springpractice.topic2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Конфигурация с использованием @Bean
@Configuration
@ComponentScan // Для сканирования @Component
public class AppConfig {

    @Bean
    public PdfGenerator pdfGenerator() {
        return new PdfGenerator();
    }

    @Bean
    public ReportService reportService(PdfGenerator pdfGenerator) {
        return new ReportService(pdfGenerator);
    }

}
