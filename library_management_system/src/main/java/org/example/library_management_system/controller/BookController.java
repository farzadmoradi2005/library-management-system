package org.example.library_management_system.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.library_management_system.dto.BookRequestDTO;
import org.example.library_management_system.dto.BookResponseDTO;
import org.example.library_management_system.dto.PageResponseDTO;
import org.example.library_management_system.model.Book;
import org.example.library_management_system.service.BookService;
import org.example.library_management_system.validation.ValidationGroups;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@Validated(ValidationGroups.OnCreate.class) @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.addBook(bookRequestDTO));
    }
    @GetMapping
    public ResponseEntity<PageResponseDTO<BookResponseDTO>> getAllBooks(@RequestParam(required = false) String title ,@PageableDefault(
            size = 10,
            sort = "title",
            direction = Sort.Direction.ASC
    ) Pageable pageable) {
        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(bookService.searchBook(title , pageable ));
        }
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Validated(ValidationGroups.OnUpdate.class) @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.updateBook(id , bookRequestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
