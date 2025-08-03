package org.portfolio.util;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PdfReport {

    public enum EstadoPrueba { PASSED, FAILED }

    private static class PasoReporte {
        String descripcion;
        EstadoPrueba estado;
        boolean capturar;
        byte[] imagenBytes;

        public PasoReporte(String descripcion, EstadoPrueba estado, boolean capturar, byte[] imagenBytes) {
            this.descripcion = descripcion;
            this.estado = estado;
            this.capturar = capturar;
            this.imagenBytes = imagenBytes;
        }
    }

    private static final List<PasoReporte> pasos = new ArrayList<>();
    private static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static void addStep(String descripcion, EstadoPrueba estado, boolean capturarPantalla) {
        byte[] imagenBytes = null;
        if (capturarPantalla && driver != null) {
            try {
                File captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                imagenBytes = Files.readAllBytes(captura.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                imagenBytes = null;
            }
        }
        pasos.add(new PasoReporte(descripcion, estado, capturarPantalla, imagenBytes));
    }

    public static void generarPDF(String nombreArchivo) {
        String carpetaTmp = "tmp";
        File carpeta = new File(carpetaTmp);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String rutaCompleta = carpetaTmp + File.separator + nombreArchivo;

        try (PdfWriter writer = new PdfWriter(rutaCompleta);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Reporte de ejecución")
                    .setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Fecha: " +
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            for (PasoReporte paso : pasos) {
                String textoPaso = "- " + paso.descripcion + " [" + paso.estado + "]";
                Paragraph p = new Paragraph(textoPaso)
                        .setFontSize(12)
                        .setFontColor(paso.estado == EstadoPrueba.PASSED ?
                                ColorConstants.GREEN : ColorConstants.RED);
                document.add(p);

                if (paso.capturar && paso.imagenBytes != null) {
                    try {
                        Image img = new Image(ImageDataFactory.create(paso.imagenBytes))
                                .scaleToFit(400, 300);
                        document.add(img);
                    } catch (Exception e) {
                        document.add(new Paragraph("[Error al cargar la imagen]")
                                .setFontColor(ColorConstants.RED));
                    }
                }
            }

            System.out.println("PDF generado correctamente: " + rutaCompleta);
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void clearReport() {
        pasos.clear();
    }
}