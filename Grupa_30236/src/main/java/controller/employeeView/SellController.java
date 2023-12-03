package controller.employeeView;

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
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SellController {

    private final BookRepositoryCacheDecorator bookRepository;
    private final List<Book> addedBooks; 
    private SellView sellView;
    private Long employeeId;
    private static final String PDF_FILE_PATH = "sales_report.pdf";


    public SellController(SellView sellView, Long id) {
        this.sellView = sellView;
        this.employeeId = id;


        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        this.addedBooks = new ArrayList<>();

        sellView.addSellButtonListener(this::handleSellButton);
        sellView.addAddButtonListener(this::handleAddButton);
    }



    private void handleSellButton(ActionEvent event) {
        List<Book> books = this.addedBooks;
        int totalPrice = 0;

        if (!new File(PDF_FILE_PATH).exists()) {
            createEmptyPDF();
        }

        try (PdfDocument pdfDocument = new PdfDocument(new PdfWriter(PDF_FILE_PATH))) {
            Document document = new Document(pdfDocument);

            for (Book book : books) {
                bookRepository.update(book.getId(), -1);
                totalPrice += book.getPret();
                appendBookToPDF(document, book);
            }

            appendSummaryToPDF(document, totalPrice, this.employeeId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEmptyPDF() {
        try (PdfDocument pdfDocument = new PdfDocument(new PdfWriter(PDF_FILE_PATH))) {
            pdfDocument.addNewPage();
        } catch (IOException e) {
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
