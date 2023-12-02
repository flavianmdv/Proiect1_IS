package controller;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.*;

import java.util.List;
import java.util.function.Consumer;

public class EmployeeController {

    private EmployeeView employeeView;
    private final BookRepository bookRepository;


    public EmployeeController(EmployeeView employeeView) {
            this.employeeView = employeeView;

            // Add event handlers for buttons
            employeeView.addAddButtonListener(this::handleAddButton);
            employeeView.addEditButtonListener(this::handleEditButton);
            employeeView.addDeleteButtonListener(this::handleDeleteButton);
            employeeView.addViewAllButtonListener(this::handleViewAllBooksButton);
            employeeView.addSellBookButtonListener(this::handleSellBookButton);
            employeeView.addCloseButtonListener(this::handleCloseButton);

            // Initialize the book repository
            this.bookRepository = new BookRepositoryCacheDecorator(
                    new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                    new Cache<>()
            );
        }


    private void handleAddButton(ActionEvent event) {
        AddBookView addBookView = new AddBookView();
        AddBookController addBookController = new AddBookController(addBookView);
        addBookView.show();
    }

    private void handleEditButton(ActionEvent event) {
        // Implement edit operation
        UpdateBookView updateBookView = new UpdateBookView();
        UpdateBookController updateBookController = new UpdateBookController(updateBookView);
//        showAlert("Edit Employee", "Edit operation not implemented yet.");
    }

    private void handleDeleteButton(ActionEvent event) {
        DeleteBookView deleteBookView = new DeleteBookView();
        DeleteBookController deleteBookController = new DeleteBookController(deleteBookView);
    }


    private void handleSellBookButton(ActionEvent event) {
        SellView sellView = new SellView();
        SellController sellController = new SellController(sellView);
    }

    private void handleViewAllBooksButton(ActionEvent event) {
        List<Book> allBooks = bookRepository.findAll();
        employeeView.setBooksInTable(allBooks);
    }
    private void handleCloseButton(ActionEvent event) {
        Stage stage = employeeView.getPrimaryStageEmployee();
        stage.close();
    }
}
