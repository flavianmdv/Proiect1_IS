package controller.employeeView;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Book;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.employee.SellView;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SellController {

    private final BookRepositoryCacheDecorator bookRepository;
    private final List<Book> addedBooks;
    private SellView sellView;
    private Long employeeId;
    private String pdfFilePath;
    public SellController(SellView sellView, Long id) {
        this.sellView = sellView;
        this.employeeId = id;
        initializePdfFilePath();

        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        this.addedBooks = new ArrayList<>();

        sellView.addSellButtonListener(this::handleSellButton);
        sellView.addAddButtonListener(this::handleAddButton);
    }

    private void initializePdfFilePath() {
        int randomSuffix = new Random().nextInt(1000);
        pdfFilePath = "Raport_Vanzari" + "_" + randomSuffix + ".pdf";
    }

    private void handleSellButton(ActionEvent event) {
        if (!new File(pdfFilePath).exists()) {
            createEmptyPDF();
        }

        try (PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdfFilePath))) {
            Document document = new Document(pdfDocument);

            int totalPrice = 0;
            for (Book book : addedBooks) {
                bookRepository.update(book.getId(), -1);
                totalPrice += book.getPret();
                appendBookToPDF(document, book);
            }

            appendSummaryToPDF(document, totalPrice, this.employeeId);
        } catch (IOException | FileNotFoundException e) {
            e.printStackTrace();
        }

        this.sellView.close();
    }

    private void createEmptyPDF() {
        try (PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdfFilePath))) {
            pdfDocument.addNewPage();
        } catch (IOException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void appendBookToPDF(Document document, Book book) {
        document.add(new Paragraph(book.toString()));
    }
    
    private void appendSummaryToPDF(Document document, int totalPrice, Long employeeId) {
        document.add(new Paragraph("Total Price: " + totalPrice)
                .setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Employee ID: " + employeeId)
                .setTextAlignment(TextAlignment.LEFT));
        document.add(new Paragraph("Date: " + LocalDateTime.now())
                .setTextAlignment(TextAlignment.LEFT));
    }

    private void handleAddButton(ActionEvent event) {
        Long bookId = sellView.getIdTextFieldValue();
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {

            addedBooks.add(book.get());
        } else {
            System.out.println("Book with ID " + bookId + " not found!");
        }

        sellView.displayAddedBooks(addedBooks);
    }


}


