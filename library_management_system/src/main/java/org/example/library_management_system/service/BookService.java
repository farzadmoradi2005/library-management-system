package org.example.library_management_system.service;

import org.example.library_management_system.dto.BookRequestDTO;
import org.example.library_management_system.dto.BookResponseDTO;

import java.util.List;

public interface BookService {

    BookResponseDTO addBook(BookRequestDTO bookRequestDTO);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO getBookById(Long id);

    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);

    void deleteBook(Long id);

    List<BookResponseDTO> searchBook(String title);
}