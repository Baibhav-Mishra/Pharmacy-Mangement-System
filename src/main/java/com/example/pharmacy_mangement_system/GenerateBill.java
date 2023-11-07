package com.example.pharmacy_mangement_system;


import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;


import com.itextpdf.kernel.pdf.PdfDocument;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;

public class GenerateBill {
    public static void generateBill(ObservableList<Medicine> arr) throws FileNotFoundException {
        String file = "src/main/resources/docs/hi.pdf";

        // Step-1 Creating a PdfDocument object
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

        // Step-2 Creating a Document object
        Document doc = new Document(pdfDoc);



        // Step-3 Creating a table
        Table table = new Table(7);

        table.addCell("  ID  ");
        table.addCell("  Type  ");
        table.addCell("  Name of Medicine  ");
        table.addCell("  Quantity  ");
        table.addCell("  Price  ");
        table.addCell("  Expiry Date  ");
        table.addCell("  Manufacturer  ");



        for(Medicine med: arr){
            table.addCell(""+med.get_id());
            table.addCell(med.getType());
            table.addCell(med.getName());
            table.addCell(""+med.getCurrentStock());
            table.addCell(""+med.getPrice());
            table.addCell(med.getExpiry());
            table.addCell(med.getManufacturer());
        }

        doc.add(table);

        // Step-7 Closing the document
        doc.close();
    }
}
