package controller;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.CartView;
import view.CustomerView;

import java.util.List;

public class CustomerController {

    private final CustomerView customerView;
    private final BookRepository bookRepository;

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;

        TableView<Book> booksTable = customerView.getBooksTable();

        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        customerView.addCloseButtonListener(this::handleCloseButton);
        customerView.addViewAllBooksButtonListener(this::handleViewAllBooksButton);
        customerView.addCartButtonListener(this::handleCartButton);
    }

    private void handleCloseButton(ActionEvent event) {

        customerView.getPrimaryStageCustomer().close();
    }

    private void handleViewAllBooksButton(ActionEvent event) {
        List<Book> allBooks = bookRepository.findAll();

        customerView.setBooksInTable(allBooks);
    }

    private void handleCartButton(ActionEvent event) {

        CartView cartView = new CartView(customerView.getList_book());
        CartController cartController = new CartController(cartView);
    }



}