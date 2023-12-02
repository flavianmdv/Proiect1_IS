package controller;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import service.book.BookServiceImpl;
import view.AddBookView;

import java.time.LocalDate;

public class AddBookController {

    private AddBookView addBookView;
    private BookRepository bookRepository;

    public AddBookController(AddBookView addBookView) {
        this.addBookView = addBookView;

        addBookView.addButtonListener(this::handleAddButton);

        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        // Set up event handlers
    }

    private void handleAddButton(ActionEvent event) {
        // Collect values from the view
        System.out.print("pula");
        String author = addBookView.getAuthorTextFieldText();
        String title = addBookView.getTitleTextFieldText();
        LocalDate publishedDate = addBookView.getPublishedDatePickerValue();
        int quantity = addBookView.getQuantityTextFieldValue();
        int price = addBookView.getPriceTextFieldValue();

        // Create a Book object
        Book book = new BookBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setPublishedDate(publishedDate)
                .setCantitate(quantity)
                .setPret(price)
                .build();

        // Save the book
        bookRepository.save(book);

        // Close the view
        addBookView.close();
    }

    public void showView() {
        addBookView.show();
    }
}
