package com.springpractice.topic2;

public class PdfGenerator {
    public void generate() {
        System.out.println("PDF generated!");
    }
}

class ReportService {
    private final PdfGenerator pdfGenerator;


    public ReportService(PdfGenerator pdfGenerator) {
        this.pdfGenerator = pdfGenerator;
    }

    public void generateReport() {
        System.out.println("Generating report: " + reportName);
        pdfGenerator.generate();
    }
}
