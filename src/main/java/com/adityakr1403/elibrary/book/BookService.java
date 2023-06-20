package com.adityakr1403.elibrary.book;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Long bookId);
}
