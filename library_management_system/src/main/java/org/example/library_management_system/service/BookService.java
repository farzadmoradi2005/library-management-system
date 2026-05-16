package org.example.library_management_system.service;

import org.example.library_management_system.dto.BookRequestDTO;
import org.example.library_management_system.dto.BookResponseDTO;
import org.example.library_management_system.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    BookResponseDTO addBook(BookRequestDTO bookRequestDTO);
    PageResponseDTO<BookResponseDTO> getAllBooks(Pageable pageable);

    BookResponseDTO getBookById(Long id);

    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);

    void deleteBook(Long id);

    PageResponseDTO<BookResponseDTO> searchBook(String title , Pageable pageable);
}