package org.example.library_management_system.service;

import lombok.RequiredArgsConstructor;
import org.example.library_management_system.model.Book;
import org.example.library_management_system.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    final private BookRepository repository;
    public Book addBook(Book book) {

        return repository.save(book);
    }
}
