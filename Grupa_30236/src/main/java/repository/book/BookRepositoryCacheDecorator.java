package repository.book;

import model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator{
    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache){
        super(bookRepository);
        this.cache = cache;
    }

    @Override
    public List<Book> findAll() {
        if (cache.hasResult()){
           return cache.load();
        }

        List<Book> books = decoratedRepository.findAll();
        cache.save(books);

        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {

        if (cache.hasResult()){
            return cache.load()
                    .stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(Book book) {
        cache.invalidateCache();
        return decoratedRepository.save(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }

    public void update(Long id, int cantitate){
        cache.invalidateCache();
        decoratedRepository.update(id, cantitate);
    }

    @Override
    public void updateBook(Long id, String titlu, String autor, LocalDate publishedDate, int cantitate, int pret) {
        cache.invalidateCache();
        decoratedRepository.updateBook(id,titlu, autor, publishedDate, cantitate, pret);
    }

    @Override
    public void deleteById(Long id) {
        cache.invalidateCache();
        decoratedRepository.deleteById(id);
    }
}
