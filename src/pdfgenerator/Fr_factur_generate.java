/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfgenerator;

import com.github.royken.converter.FrenchNumberToWords;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import dbclasse.Client;
import dbclasse.Vent;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IT-06
 */
public class Fr_factur_generate {

    Document document;
    String path = "pdf_test.pdf";

    public void generate_fr_factur(
            Client c,
            String date,
            String noFact,
            Vector<Vent> v,
            DefaultTableModel model,
            Float mtht,
            int tva,
            float p_tva,
            float mtttc
    ) throws MalformedURLException, FileNotFoundException {

        File file = new File("F"+noFact+"_"+date+"_"+c.getIdClient()+".pdf");

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

        addEmptyLine(5);

        //*********************************************************
        para = new Paragraph();
        text = new Text("DOIT: ").setBold();
        para.add(text);
//        text = new Text("Direction des Oevres Universitaires deTebessa" + "\n");
        text = new Text(c.getDoit() + "\n");
        para.add(text);

        text = new Text("Au Profil: ").setBold();
        para.add(text);
//        text = new Text("Résidence Universitaire Mekahlia Ibrahim (Restaurant central)" + "\n");
        text = new Text(c.getProfil() + "\n");
        para.add(text);

        para.setTextAlignment(TextAlignment.CENTER);
        document.add(para);

        //*********************************************************
        para = new Paragraph();
        text = new Text("Mois: ").setBold();
        para.add(text);
//        text = new Text("Janvier / 2022" + "\n");
        text = new Text(getMonth_fr(date) + "\n");
        para.add(text);

        text = new Text("Facture N°: ").setBold();
        para.add(text);
//        text = new Text("001");
        text = new Text(noFact);
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
        Paragraph para8 = new Paragraph("taxe sur la valeur ajoutee " + tva + " %").setBold();
        Paragraph para9 = new Paragraph("montant total en tout taxes comprises").setBold();

        Table table2 = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

        table2.addCell(new Cell().add(para1).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para2).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para3).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para4).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para5).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(para6).setTextAlignment(TextAlignment.CENTER));
        //********************************
        /**
         *
         */
//        for (int i = 0; i < v.size(); i++) {
//            Vent ve = v.get(i);
//            table2.addCell(new Cell().add(new Paragraph("" + (i + 1))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
//            if (i == 0) {
//                table2.addCell(new Cell(v.size(), 1).add(new Paragraph(ve.getIdProduit())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(50)));
//            }
//            table2.addCell(new Cell().add(new Paragraph("KG")).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
//            table2.addCell(new Cell().add(new Paragraph("" + ve.getQte())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(10)));
//            table2.addCell(new Cell().add(new Paragraph("" + ve.getPrixU())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
//            table2.addCell(new Cell().add(new Paragraph("" + ve.getMontant())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
//        }
        for (int i = 0; i < model.getRowCount(); i++) {
            table2.addCell(new Cell().add(new Paragraph("" + (i + 1))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
            table2.addCell(new Cell().add(new Paragraph(model.getValueAt(i, 1).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(50)));
            table2.addCell(new Cell().add(new Paragraph("KG")).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
            table2.addCell(new Cell().add(new Paragraph("" + model.getValueAt(i, 2).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(10)));
            table2.addCell(new Cell().add(new Paragraph("" + model.getValueAt(i, 3).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
            table2.addCell(new Cell().add(new Paragraph("" + model.getValueAt(i, 4).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));

        }

        //********************************
        table2.addCell(new Cell(1, 5).add(para7).setTextAlignment(TextAlignment.CENTER));
//        table2.addCell(new Cell().add(para0).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(new Paragraph("" + mtht).setBold()).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell(1, 5).add(para8).setTextAlignment(TextAlignment.CENTER));
//        table2.addCell(new Cell().add(para0).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(new Paragraph("" + p_tva)).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell(1, 5).add(para9).setTextAlignment(TextAlignment.CENTER));
//        table2.addCell(new Cell().add(para0).setTextAlignment(TextAlignment.CENTER));
        table2.addCell(new Cell().add(new Paragraph("" + mtttc).setBold()).setTextAlignment(TextAlignment.CENTER));
        document.add(table2);

        addEmptyLine(5);

        //******************************
        para = new Paragraph();
        text = new Text("Arrêtée la Présente Facture a la Somme de: ").setBold();
        para.add(text);
        text = new Text(convert_chiffre2letter(mtttc) + "\n");
        para.add(text);

        para.setTextAlignment(TextAlignment.LEFT);
        document.add(para);

        addEmptyLine(5);

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

    String convert_chiffre2letter(float montant) {
        //******** montant en letter 

        float nbr = montant;// Float.parseFloat(jTextField34.getText());

        String doubleAsString = String.valueOf(nbr);
        int indexOfDecimal = doubleAsString.indexOf(".");

        String dinar = FrenchNumberToWords.convert((long) nbr);

        int int_centime = Integer.parseInt(doubleAsString.substring(indexOfDecimal + 1));
        String centime = ".";

        if (int_centime != 0) {
            centime = "et " + FrenchNumberToWords.convert(int_centime) + " centimes";
        }

        return (dinar + ", dinars algériens " + centime); // en FR

    }

    String getMonth_fr(String date) {
        int m = Integer.parseInt(date.split("-")[0]);
        String m_text = "";
        switch (m) {
            case 1:
                m_text = "Janvier";
                break;
            case 2:
                m_text = "Février";
                break;
            case 3:
                m_text = "Mars";
                break;
            case 4:
                m_text = "Avril";
                break;
            case 5:
                m_text = "Mai";
                break;
            case 6:
                m_text = "Juin";
                break;
            case 7:
                m_text = "juillet";
                break;
            case 8:
                m_text = "Aout";
                break;
            case 9:
                m_text = "Septembre";
                break;
            case 10:
                m_text = "Octobre";
                break;
            case 11:
                m_text = "Novembre";
                break;
            case 12:
                m_text = "Décembre";
                break;
        }

        return m_text + " / " + date.split("-")[1];
    }
}