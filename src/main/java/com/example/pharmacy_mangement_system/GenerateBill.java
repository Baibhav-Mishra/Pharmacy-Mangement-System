package com.example.pharmacy_mangement_system;

import java.util.Random;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;


import com.itextpdf.kernel.pdf.PdfDocument;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;


public class GenerateBill {
    public static void generateBill(ObservableList<Medicine> arr, double total) throws FileNotFoundException {
        Random rand = new Random();

        String file = "src/main/resources/docs/bill"+rand.nextInt(1000)+".pdf";

        // Step-1 Creating a PdfDocument object
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));

        // Step-2 Creating a Document object
        Document doc = new Document(pdfDoc);



        // Step-3 Creating a table
        Table table = new Table(7);

        table.addCell("  ID   ");
        table.addCell("  Type     ");
        table.addCell("  Name of Medicine  ");
        table.addCell("  Quantity  ");
        table.addCell("  Price   ");
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

        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("Total: ");
        table.addCell(""+total);

        doc.add(table);

        // Step-7 Closing the document
        doc.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bill Generated");
        alert.setHeaderText("The bill has been generated");
//        alert.setContentText("Please enter a new username");
        alert.showAndWait();
    }
}
