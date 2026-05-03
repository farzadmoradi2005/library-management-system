package org.example.library_management_system.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.library_management_system.dto.BookRequestDTO;
import org.example.library_management_system.dto.BookResponseDTO;
import org.example.library_management_system.model.Book;
import org.example.library_management_system.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping("post")
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        System.out.println(";;;;");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.addBook(bookRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(@RequestParam(required = false) String title) {
        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(bookService.searchBook(title));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.updateBook(id , bookRequestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
