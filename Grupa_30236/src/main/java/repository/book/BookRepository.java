package repository.book;

import model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean save(Book book);

    void removeAll();

    void update(Long id, int cantitate);
    void updateBook(Long id, String titlu, String autor, LocalDate publishedDate, int cantitate, int pret);

}
