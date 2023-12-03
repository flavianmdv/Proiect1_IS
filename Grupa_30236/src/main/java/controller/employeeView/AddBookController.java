package controller.employeeView;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.employee.AddBookView;

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

    }

    private void handleAddButton(ActionEvent event) {
        String author = addBookView.getAuthorTextFieldText();
        String title = addBookView.getTitleTextFieldText();
        LocalDate publishedDate = addBookView.getPublishedDatePickerValue();
        int quantity = addBookView.getQuantityTextFieldValue();
        int price = addBookView.getPriceTextFieldValue();

        Book book = new BookBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setPublishedDate(publishedDate)
                .setCantitate(quantity)
                .setPret(price)
                .build();

        bookRepository.save(book);

        addBookView.close();
    }

}
