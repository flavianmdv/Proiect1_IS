// UpdateBookController.java
package controller.employeeView;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import model.Book;
import model.builder.BookBuilder;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import view.employee.UpdateBookView;

import java.time.LocalDate;

public class UpdateBookController {

    private UpdateBookView updateBookView;
    private BookRepository bookRepository;

    public UpdateBookController(UpdateBookView updateBookView) {
        this.updateBookView = updateBookView;

        updateBookView.setUpdateButtonHandler(this::handleUpdateButton);

        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

    }

    private void handleUpdateButton(ActionEvent event) {
        String id = updateBookView.getIdTextFieldText();
        String author = updateBookView.getAuthorTextFieldText();
        String title = updateBookView.getTitleTextFieldText();
        LocalDate publishedDate = updateBookView.getPublishedDatePickerValue();
        int quantity = updateBookView.getQuantityTextFieldValue();
        int price = updateBookView.getPriceTextFieldValue();

        Book book = new BookBuilder()
                .setId(Long.parseLong(id))
                .setTitle(title)
                .setAuthor(author)
                .setPublishedDate(publishedDate)
                .setCantitate(quantity)
                .setPret(price)
                .build();

        bookRepository.updateBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPublishedDate(), book.getCantitate(), book.getPret());

        updateBookView.close();
    }


}
