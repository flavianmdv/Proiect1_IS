package controller;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.DeleteBookView;

public class DeleteBookController {

    private DeleteBookView deleteBookView;
    private BookRepository bookRepository;

    public DeleteBookController(DeleteBookView deleteBookView) {
        this.deleteBookView = deleteBookView;

        deleteBookView.addDeleteButtonListener(this::handleDeleteButton);

        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );
    }

    private void handleDeleteButton(ActionEvent event) {
        Long bookId = deleteBookView.getIdTextFieldValue();

        bookRepository.deleteById(bookId);

        deleteBookView.close();
    }


}
