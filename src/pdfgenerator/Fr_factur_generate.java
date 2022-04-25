/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfgenerator;

import com.itextpdf.io.image.*;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import dbclasse.Facture;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 *
 * @author IT-06
 */
public class Fr_factur_generate {

    Document document;
    String path = "pdf_test.pdf";

    public void generate_fr_factur() throws MalformedURLException, FileNotFoundException {

        File file = new File(path);

        PdfWriter pdfWriter = new PdfWriter(file);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        pdfDocument.addNewPage();

        document = new Document(pdfDocument);

        Paragraph para = new Paragraph("HEBBIR ABDESSELAM");
        para.setTextAlignment(TextAlignment.CENTER);
        para.setBold();
        document.add(para);

        para = new Paragraph("Vénète en gros des produits liés à l'alimentation humaine\n"
                + "Zone D'activite Artisanal N°176-Ain M'lila");
        para.setFontSize(14);
        para.setTextAlignment(TextAlignment.CENTER);
        document.add(para);

        para = new Paragraph("Tel: 05 50 50 32 54 - Fax: 032 51 12 12");
        para.setBold().setFontSize(14);
        para.setTextAlignment(TextAlignment.CENTER);
        document.add(para);

        document.close();
    }

    private void addEmptyLine(int number) {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" ").setFontSize(20));

        }
    }
}
