// UpdateBookController.java
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
import view.UpdateBookView;

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

        // Set up event handlers
    }

    private void handleUpdateButton(ActionEvent event) {
        // Collect values from the view
        String id = updateBookView.getIdTextFieldText();  // Assuming id is a string; adjust as needed
        String author = updateBookView.getAuthorTextFieldText();
        String title = updateBookView.getTitleTextFieldText();
        LocalDate publishedDate = updateBookView.getPublishedDatePickerValue();
        int quantity = updateBookView.getQuantityTextFieldValue();
        int price = updateBookView.getPriceTextFieldValue();

        // Create a Book object
        Book book = new BookBuilder()
                .setId(Long.parseLong(id))  // Parse id to Long; adjust as needed
                .setTitle(title)
                .setAuthor(author)
                .setPublishedDate(publishedDate)
                .setCantitate(quantity)
                .setPret(price)
                .build();

        // Update the book
        bookRepository.updateBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPublishedDate(), book.getCantitate(), book.getPret());

        // Close the view
        updateBookView.close();
    }

    public void showView() {
        updateBookView.show();
    }

//    public void setBookToUpdate(Book book) {
//        // Set the book details in the view for updating
//        updateBookView.setIdTextFieldText(String.valueOf(book.getId()));  // Assuming id is a string; adjust as needed
//        updateBookView.setTitleTextFieldText(book.getTitle());
//        updateBookView.setAuthorTextFieldText(book.getAuthor());
//        updateBookView.setPublishedDatePickerValue(book.getPublishedDate());
//        updateBookView.setQuantityTextFieldValue(book.getCantitate());
//        updateBookView.setPriceTextFieldValue(book.getPret());
//        updateBookView.setExtraTextFieldText(book.getExtraField());  // Additional field
//    }
}
