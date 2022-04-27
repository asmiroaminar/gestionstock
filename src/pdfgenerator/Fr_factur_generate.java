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
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
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
        //************************************************************************
        para = new Paragraph();
        Text text = new Text("N°RC: ").setBold();
        para.add(text);
        text = new Text("04/00-2018202 A99" + "\n");
        para.add(text);

        text = new Text("NIF: ").setBold();
        para.add(text);
        text = new Text("177041201983465" + "\n");
        para.add(text);

        text = new Text("NIS: ").setBold();
        para.add(text);
        text = new Text("7 983 0412 00065 33" + "\n");
        para.add(text);

        text = new Text("N° RIP: ").setBold();
        para.add(text);
        text = new Text("004003354000751211-76" + "\n");
        para.add(text);

        text = new Text("CPA - Agence de Ain M'lila");
        para.add(text);

        para.setTextAlignment(TextAlignment.LEFT);
        document.add(para);

        //*********************************************************
        para = new Paragraph();
        text = new Text("DOIT: ").setBold();
        para.add(text);
        text = new Text("Direction des Oevres Universitaires deTebessa" + "\n");
        para.add(text);

        text = new Text("Au Profil: ").setBold();
        para.add(text);
        text = new Text("Résidence Universitaire Mekahlia Ibrahim (Restaurant central)" + "\n");
        para.add(text);

        para.setTextAlignment(TextAlignment.CENTER);
        document.add(para);

        //*********************************************************
        para = new Paragraph();
        text = new Text("Mois: ").setBold();
        para.add(text);
        text = new Text("Janvier / 2022" + "\n");
        para.add(text);

        text = new Text("Facture N°: ").setBold();
        para.add(text);
        text = new Text("001");
        para.add(text);

        para.setTextAlignment(TextAlignment.CENTER);
        document.add(para);
        //*************************************************
        Paragraph para0 = new Paragraph("0.00");
        Paragraph para1 = new Paragraph("N°").setBold();
        Paragraph para2 = new Paragraph("Désignnation").setBold();
        Paragraph para3 = new Paragraph("Unite").setBold();
        Paragraph para4 = new Paragraph("Qte").setBold();
        Paragraph para5 = new Paragraph("Prix u HT").setBold();
        Paragraph para6 = new Paragraph("Montant").setBold();
        Paragraph para7 = new Paragraph("montant total hors taxes").setBold();
        Paragraph para8 = new Paragraph("taxe sur la valeur ajoutee").setBold();
        Paragraph para9 = new Paragraph("montant total en tout taxes comprises").setBold();
        
        Table table2 = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

        table2.addCell(new Cell().add(para1).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para2).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para3).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para4).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para5).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para6).setTextAlignment(TextAlignment.CENTER));
        //********************************
        
        //********************************
        table2.addCell(new Cell(1,5).add(para7).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para0).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell(1,5).add(para8).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para0).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell(1,5).add(para9).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para0).setTextAlignment(TextAlignment.CENTER));
        
        document.add(table2);
        
        
        //******************************
        
        para = new Paragraph();
        text = new Text("Arrêtée la Présente Facture a la Somme de: ").setBold();
        para.add(text);
        text = new Text("Zero ,dinars algériens" + "\n");
        para.add(text);
        
        para.setTextAlignment(TextAlignment.LEFT);
        document.add(para);
        
        para = new Paragraph("Fournisseur");
        para.setTextAlignment(TextAlignment.RIGHT);
        document.add(para);
        

        document.close();
    }

    private void addEmptyLine(int number) {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" ").setFontSize(20));

        }
    }
}
