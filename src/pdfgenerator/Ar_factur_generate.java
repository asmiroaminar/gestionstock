/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfgenerator;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import dbclasse.Client;
import dbclasse.Vent;
import dbmanipulation.DataServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ar_factur_generate {

//    public static final String DEST = "arabic_example.pdf";
    public static final String FONT = "font/ARIAL.ttf";//NotoNaskhArabic-Regular

    static NumberFormat nf = NumberFormat.getCurrencyInstance();
    static DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();

    DataServices ds = new DataServices();

    Document doc;

    //****************************************************************
    String convert_chiffre2letter(String m) {
        //******** montant en letter 

        float nbr = Float.parseFloat(m);

        String doubleAsString = String.valueOf(nbr);
        int indexOfDecimal = doubleAsString.indexOf(".");

        int hh = 1000;

        String dinar = "";
        try {
            dinar = Nombrearabic.CALCULATE.getValue((long) nbr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        int int_centime = Integer.parseInt(doubleAsString.substring(indexOfDecimal + 1));
        String centime = ".";

        if (int_centime != 0) {
            try {
                centime = "??" + " " + Nombrearabic.CALCULATE.getValue(int_centime) + " " + "??????????.";
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        return dinar + " " + "???????????? ????????????" + " " + centime;

    }
    //****************************************************************

    public void generate_ar_factur(
            Client c,
            String date,
            String noFact,
            Vector<Vent> v,
            DefaultTableModel model,
            Float mtht,
            int tva,
            float p_tva,
            float mtttc,
            boolean detail,
            String jour
    ) {

        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);

        try {
            String folder_path = ds.getFolderPath();

            String path = folder_path + "\\" + "F" + noFact + "_" + date + "_" + c.getIdClient() + ".pdf";

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(path));
            doc = new Document(pdfDoc);
            PdfFont f = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
            LanguageProcessor al = new com.itextpdf.text.pdf.languages.ArabicLigaturizer();
            // It is required to add iText typography dependency to handle correctly arabic text

//        Paragraph p = new Paragraph();
//        p.setTextAlignment(TextAlignment.CENTER);
//        p.add(new Text(al.process("???????? ?????? ??????????")).setFont(f));
//        doc.add(p);
            Paragraph para = new Paragraph();
            Text text = new Text(al.process("???????? ?????? ????????????")).setFont(f);
            para.setTextAlignment(TextAlignment.CENTER);
            para.setBold();
            para.setFontSize(12);
            para.add(text);

            doc.add(para);

            para = new Paragraph(new Text(al.process("?????????? ?????????????? ???????????????? ???????????????? ???????????? ??????????????")).setFont(f));
            para.setTextAlignment(TextAlignment.CENTER);
            para.setBold();
            para.setFontSize(14);
            doc.add(para);
            //-----------------------------------------------
            Table table2 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

            table2.addCell(
                    new Cell()
                    .add(new Paragraph(new Text(al.process("??????????????")).setFont(f)))
                    .setTextAlignment(TextAlignment.CENTER));
            table2.addCell(
                    new Cell()
                    .add(new Paragraph(new Text(al.process("????????????")).setFont(f)))
                    .setTextAlignment(TextAlignment.CENTER));
            //----------------------------------------------

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process(c.getDoit())).setFont(f))
                            .add(new Text(al.process("?????? ??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process("???????? ?????? ????????????")).setFont(f))
                            .add(new Text(al.process("?????? ??????????????: ")).setFont(f).setBold())
                            .add(new Text("\n" + al.process("?????????? ?????????????? ???????????????? ???????????????? ???????????? ??????????????")).setFont(f))
                    )
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //-------------------------------------------------------------

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process(c.getProfil())).setFont(f))
                            .add(new Text(al.process("???????? ??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process("?????????? ???????????????? ?????????????? ?????? 173 ?????? ??????????")).setFont(f))
                            .add(new Text(al.process("??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process(c.getFormeJuridique())).setFont(f))
                            .add(new Text(al.process("?????????? ????????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("0550 50 32 54").setFont(f))
                            .add(new Text(al.process("????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process(c.getAdr())).setFont(f))
                            .add(new Text(al.process("??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("032 51 12 12").setFont(f))
                            .add(new Text(al.process("????????/????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(c.getTel()).setFont(f))
                            .add(new Text(al.process("????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("00/04-2018202 99").setFont(f))
                            .add(new Text(al.process("?????? ?????????? ??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(c.getFax()).setFont(f))
                            .add(new Text(al.process("????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("2017/01/18").setFont(f))
                            .add(new Text(al.process("?????????? ????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process(c.getNif())).setFont(f))
                            .add(new Text(al.process("?????? ?????????????? ??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("177041201983465").setFont(f))
                            .add(new Text(al.process("?????????? ??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("").setFont(f))
                            .add(new Text(al.process("?????? ?????? ??????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("798304120006533").setFont(f))
                            .add(new Text(al.process("?????????? ????????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("").setFont(f))
                            .add(new Text(al.process("????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text("004003354000751211-76").setFont(f))
                            .add(new Text(al.process("??.??.????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));
            //----------------------------------------------------------------------
            table2.addCell(
                    new Cell()
                    .add(new Paragraph(new Text(al.process("")).setFont(f)))
                    .setTextAlignment(TextAlignment.RIGHT));

            table2.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process("?????????? ???????????? ???????????????? ?????? ??????????")).setFont(f))
                            .add(new Text(al.process("??????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(10));

            doc.add(table2);

            //****************************************************************************
            addEmptyLine(2);

            Table table3 = new Table(1).setHorizontalAlignment(HorizontalAlignment.CENTER).setWidth(150);

            table3.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(noFact).setFont(f))
                            .add(new Text(al.process("???????????? ??????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.CENTER));
            if(!jour.equals(""))
            table3.addCell(
                    new Cell()
                    .add(new Paragraph()
                            .add(new Text(al.process(getMonth_ar(date)) + " "+ jour).setFont(f))
                            .add(new Text("  " + al.process("????????????: ")).setFont(f).setBold()))
                    .setTextAlignment(TextAlignment.CENTER));
            else
                table3.addCell(
                    new Cell()
                    .add(new Paragraph()
                            
                            .add(new Text(":" + al.process("????????????") + "      ").setFont(f).setBold())
                            .add(new String("               "))
                    )
                    .setTextAlignment(TextAlignment.RIGHT));

            doc.add(table3);

            //*********************   FACTur     *********************************
            addEmptyLine(2);

            Paragraph p1 = new Paragraph(new Text(al.process("???????????? / ??.??")).setFont(f).setBold());
            Paragraph p2 = new Paragraph(new Text(al.process("?????? ???????????? / ??.??")).setFont(f).setBold());
            Paragraph p3 = new Paragraph(new Text(al.process("????????????")).setFont(f).setBold());
            Paragraph p4 = new Paragraph(new Text(al.process("????????????")).setFont(f).setBold());
            Paragraph p5 = new Paragraph(new Text(al.process("??????????????")).setFont(f).setBold());
            Paragraph p6 = new Paragraph(new Text(al.process("??????????????")).setFont(f).setBold());
            Paragraph p7 = new Paragraph(new Text(al.process("??????")).setFont(f).setBold());

            Paragraph p8 = new Paragraph(new Text(al.process("??????????????")).setFont(f).setBold());
            Paragraph p9 = new Paragraph(new Text(al.process("?????????????? ???????? ????????????")).setFont(f).setBold());
            Paragraph p10 = new Paragraph(new Text(al.process("???????????? ?????? ???????????? ?????????????? " + tva + " % ")).setFont(f).setBold());
            Paragraph p11 = new Paragraph(new Text(al.process("?????????????? ?????? ????????????")).setFont(f).setBold());

//     les information
            if (detail) {
                Table table4 = new Table(UnitValue.createPercentArray(7)).useAllAvailableWidth();

                //     La tete de tableau
                table4.addCell(new Cell().add(p1).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p2).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p3).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p4).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p5).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p6).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p7).setTextAlignment(TextAlignment.CENTER));

                for (int i = 0; i < v.size(); i++) {
                    Vent ve = v.get(i);
                    table4.addCell(new Cell().add(new Paragraph("" + nf.format(ve.getMontant()))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
                    table4.addCell(new Cell().add(new Paragraph("" + nf.format(ve.getPrixU()))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
                    table4.addCell(new Cell().add(new Paragraph("" + ve.getQte())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(10)));
                    table4.addCell(new Cell().add(new Paragraph(new Text(al.process("??????")).setFont(f))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
                    table4.addCell(new Cell().add(new Paragraph("" + ve.getDate_vent())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));

                    if (i == 0) {
                        table4.addCell(new Cell(v.size(), 1).add(new Paragraph(new Text(al.process(ve.getIdProduit())).setFont(f))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(50)));
                    }
                    if (i < 10) {
                        table4.addCell(new Cell().add(new Paragraph("0" + (i + 1))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
                    } else {
                        table4.addCell(new Cell().add(new Paragraph("" + (i + 1))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
                    }

                }
                //      totale       *-*-*-*-*-*-*-*
                table4.addCell(new Cell()
                        .add(new Paragraph("" + nf.format(mtht)))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(
                        new Cell()
                        .add(new Paragraph("/"))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(
                        new Cell()
                        .add(new Paragraph("" + model.getValueAt(0, 2).toString()))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 4).add(p8).setTextAlignment(TextAlignment.CENTER));
                //     
                //      totale 
                table4.addCell(new Cell()
                        .add(new Paragraph("" + nf.format(mtht)))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 6).add(p9).setTextAlignment(TextAlignment.CENTER));
                if(p_tva!= 0)
                    table4.addCell(new Cell()
                        .add(new Paragraph("" + nf.format(p_tva)))
                        .setTextAlignment(TextAlignment.CENTER));
                else
                    table4.addCell(new Cell()
                        .add(new Paragraph("/"))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 6).add(p10).setTextAlignment(TextAlignment.CENTER));

                table4.addCell(new Cell()
                        .add(new Paragraph("" + nf.format(mtttc)))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 6).add(p11).setTextAlignment(TextAlignment.CENTER));

                doc.add(table4);
            } else {
                // Not detail

                Table table4 = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();

                //     La tete de tableau
                table4.addCell(new Cell().add(p1).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p2).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p3).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p6).setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell().add(p7).setTextAlignment(TextAlignment.CENTER));
                for (int i = 0; i < model.getRowCount(); i++) {
                    table4.addCell(new Cell().add(new Paragraph(model.getValueAt(i, 4).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
                    table4.addCell(new Cell().add(new Paragraph(model.getValueAt(i, 3).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(15)));
                    table4.addCell(new Cell().add(new Paragraph(model.getValueAt(i, 2).toString())).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(10)));
                    table4.addCell(new Cell().add(new Paragraph(new Text(al.process(model.getValueAt(i, 1).toString())).setFont(f))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(50)));
                    if (i < 10) {
                        table4.addCell(new Cell().add(new Paragraph("0" + (i + 1))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
                    } else {
                        table4.addCell(new Cell().add(new Paragraph("" + (i + 1))).setTextAlignment(TextAlignment.CENTER).setWidth(UnitValue.createPercentValue(5)));
                    }

                }
                //      totale 
                table4.addCell(
                        new Cell()
                        .add(new Paragraph("" + nf.format(mtht)))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 4).add(p9).setTextAlignment(TextAlignment.CENTER));

                table4.addCell(
                        new Cell()
                        .add(new Paragraph("" + nf.format(p_tva)))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 4).add(p10).setTextAlignment(TextAlignment.CENTER));

                table4.addCell(
                        new Cell()
                        .add(new Paragraph("" + nf.format(mtttc)))
                        .setTextAlignment(TextAlignment.CENTER));
                table4.addCell(new Cell(1, 4).add(p11).setTextAlignment(TextAlignment.CENTER));

                doc.add(table4);
            }

            //********************************************************
            //******************************
            /**
             * int nbr = Integer.parseInt(jTextField32.getText()); try { //
             * jLabel14.setText(FrenchNumberToWords.convert(nbr) + " DA."); //
             * en FR jLabel14.setText(Nombrearabic.CALCULATE.getValue(nbr)+"
             * ???????????? ????????????. "); // en AR } catch (Exception ex) {
             * Logger.getLogger(Facture_vente_ar.class.getName()).log(Level.SEVERE,
             * null, ex); }
             */
            para = new Paragraph();

            text = new Text(al.process(convert_chiffre2letter("" + mtttc))).setFont(f);
            para.add(text);
            text = new Text(al.process("?????? ???????? ???????????????? ???? : ")).setFont(f).setBold();
            para.add(text);

            para.setTextAlignment(TextAlignment.RIGHT);
            doc.add(para);

            addEmptyLine(5);

            para = new Paragraph(new Text(al.process("??????????????????")).setFont(f));
            para.setTextAlignment(TextAlignment.LEFT);
            doc.add(para);
            doc.close();

            JOptionPane.showMessageDialog(null, "Bien Eregistrer");
            
            Runtime.getRuntime().exec("explorer.exe /select," + path);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void addEmptyLine(int number) {
        for (int i = 0; i < number; i++) {
            doc.add(new Paragraph(" ").setFontSize(20));

        }
    }

    String getMonth_ar(String date) {
        int m = Integer.parseInt(date.split("-")[0]);
        String m_text = "";
        switch (m) {
            case 1:
                m_text = "??????????";
                break;
            case 2:
                m_text = "??????????";
                break;
            case 3:
                m_text = "????????";
                break;
            case 4:
                m_text = "??????????";
                break;
            case 5:
                m_text = "??????";
                break;
            case 6:
                m_text = "????????";
                break;
            case 7:
                m_text = "????????????";
                break;
            case 8:
                m_text = "??????";
                break;
            case 9:
                m_text = "????????????";
                break;
            case 10:
                m_text = "????????????";
                break;
            case 11:
                m_text = "????????????";
                break;
            case 12:
                m_text = "????????????";
                break;
        }

        return m_text + " " + date.split("-")[1];
    }

}
