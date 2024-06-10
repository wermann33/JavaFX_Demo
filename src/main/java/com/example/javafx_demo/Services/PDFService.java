package com.example.javafx_demo.Services;

import com.example.javafx_demo.BL.IUserService;
import com.example.javafx_demo.BL.UserService;
import com.example.javafx_demo.BL.models.UserModel;
import com.example.javafx_demo.DefaultInjector;
import com.example.javafx_demo.Injectable;
import com.example.javafx_demo.MainApp;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.colors.ColorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Objects;

public class PDFService implements IPDFService, Injectable {
    private final IUserService userService;
    private final Logger logger;


    public PDFService() {
        this.userService = DefaultInjector.getService(UserService.class);
        logger = LoggerFactory.getLogger(PDFService.class);
        logger.info("PDFService initialized");
    }

    @Override
    public void createUserListPDF(String filePath) throws Exception {
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        logger.info("Creating PDF at {}", filePath);

        //Bild hinzufügen
        ImageData imageData = ImageDataFactory.create(Objects.requireNonNull(
                MainApp.class.getResource("img/Logo.png")));
        Image image = new Image(imageData).setHorizontalAlignment(HorizontalAlignment.CENTER);
        image.scaleToFit(200, 200);
        document.add(image);

        //Titel
        document.add(new Paragraph("User List: ").setBold().setFontSize(14).setTextAlignment(
                TextAlignment.LEFT
        ));

        //Tabelle
        List<UserModel> users = userService.getAllUsers();
        Table table = new Table(2);
        //Tabellenkopf
        table.addHeaderCell(new Cell().add(new Paragraph("Username").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));
        table.addHeaderCell(new Cell().add(new Paragraph("Password").setBold().
                setTextAlignment(TextAlignment.CENTER).setBackgroundColor(ColorConstants.LIGHT_GRAY)));

        //Tabellenzeilen
        for (UserModel user : users){
            table.addCell(new Cell().add(new Paragraph(user.getUsername()).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell().add(new Paragraph(user.getPassword()).setTextAlignment(TextAlignment.CENTER)));

        }

        //Tabelle zum Dokument hinzufügen
        document.add(table);
        document.close();
        logger.info("PDF created successfully at {}", filePath);
    }
}
