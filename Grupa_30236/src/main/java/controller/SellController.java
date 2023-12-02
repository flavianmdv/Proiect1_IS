package controller;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import service.book.BookService;
import view.SellView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SellController {

    private final BookRepositoryCacheDecorator bookRepository;
    private final List<Book> addedBooks;
    private SellView sellView;

    public SellController(SellView sellView) {
        this.sellView = sellView;

        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        this.addedBooks = new ArrayList<>();

        sellView.addSellButtonListener(this::handleSellButton);
        sellView.addAddButtonListener(this::handleAddButton);
    }

    private void handleSellButton(ActionEvent event) {
        Long bookId = sellView.getIdTextFieldValue();
        Optional<Book> book = bookRepository.findById(bookId);




        sellView.displayAddedBooks(addedBooks);
    }

    private void handleAddButton(ActionEvent event) {
        Long bookId = sellView.getIdTextFieldValue();
        Optional<Book> book = bookRepository.findById(bookId);



        sellView.displayAddedBooks(addedBooks);
    }


}
