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
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.text.pdf.languages.LanguageProcessor;

import java.io.File;

public class Ar_factur_generate {

    public static final String DEST = "arabic_example.pdf";
    public static final String FONT = "font/NotoNaskhArabic-Regular.ttf";

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfFont f = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
        LanguageProcessor al = new com.itextpdf.text.pdf.languages.ArabicLigaturizer();
        // It is required to add iText typography dependency to handle correctly arabic text

//        Paragraph p = new Paragraph();
//        p.setTextAlignment(TextAlignment.CENTER);
//        p.add(new Text(al.process("هبير نصر الدين")).setFont(f));
//        doc.add(p);
        Paragraph para = new Paragraph();
        Text text = new Text(al.process("هبير عبد السلام")).setFont(f);
        para.setTextAlignment(TextAlignment.CENTER);
        para.setBold();
        para.add(text);
        
        
        doc.add(para);

        para = new Paragraph(new Text(al.process("تجارة بالجملة للمنتجات المرتبطة بتغذية الإنسان")).setFont(f));
        para.setTextAlignment(TextAlignment.CENTER);
        para.setBold();
        doc.add(para);

        //-----------------------------------------------
        Table table2 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(al.process("المشتري")).setFont(f)))
                .setTextAlignment(TextAlignment.CENTER));
        table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(al.process("البائع")).setFont(f)))
                .setTextAlignment(TextAlignment.CENTER));

        table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(al.process(""+"اسم المؤسسة: ")).setFont(f)))
                .setTextAlignment(TextAlignment.RIGHT));

        table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(al.process(""+"اسم المؤسسة: ")).setFont(f)))
                .setTextAlignment(TextAlignment.RIGHT));
        
         table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(al.process(""+"موقع التوزيع: ")).setFont(f)))
                .setTextAlignment(TextAlignment.RIGHT));

        table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(al.process(""+"العنوان: ")).setFont(f)))
                .setTextAlignment(TextAlignment.RIGHT));
        
         table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(""+"الشكل القانوني: ").setFont(f)))
                .setTextAlignment(TextAlignment.RIGHT));

        table2.addCell(
                new Cell()
                .add(new Paragraph(new Text(""+"الهاتف: ").setFont(f)))
                .setTextAlignment(TextAlignment.RIGHT));

        doc.add(table2);

        doc.close();
    }

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);

        //file.getParentFile().mkdirs();
        new Ar_factur_generate().manipulatePdf(DEST);
    }
}
