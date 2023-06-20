package com.adityakr1403.elibrary.book;

import com.adityakr1403.elibrary.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id))
                .orElseThrow(() ->
                        new BookNotFoundException("Book with id " + id + " not found"));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        Optional<Book> theBook = bookRepository.findById(bookId);
        theBook.ifPresent(book->bookRepository.deleteById(book.getBookId()));
    }
}
