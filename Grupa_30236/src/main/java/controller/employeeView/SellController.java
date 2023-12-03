package controller.employeeView;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.employee.SellView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        if (!Files.exists(Paths.get(PDF_FILE_PATH))) {
            createEmptyPDF();
        }
        for (Book book : books){

            bookRepository.update(book.getId(), -1);

            totalPrice = book.getPret();
            appendBookToPDF(book);
        }
        appendSummaryToPDF(totalPrice, this.employeeId);
    }

    private void createEmptyPDF() {
        try (PDDocument document = new PDDocument()) {
            document.addPage(new PDPage());
            document.save(PDF_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendBookToPDF(Book book) {
        try (PDDocument document = PDDocument.load(new File(PDF_FILE_PATH))) {
            PDPage page = document.getPage(0);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();


                contentStream.newLineAtOffset(20, 700);
                contentStream.showText(book.toString());
                contentStream.newLine();

                contentStream.endText();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendSummaryToPDF(int totalPrice, Long employeeId) {
        try (PDDocument document = PDDocument.load(new File(PDF_FILE_PATH))) {
            PDPage page = document.getPage(0);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                contentStream.beginText();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();

                contentStream.newLine();
                contentStream.showText("Total Price: " + totalPrice);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();

                contentStream.showText("Employee ID: " + employeeId);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Date: " + LocalDateTime.now());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.endText();
            }

            document.save(PDF_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
