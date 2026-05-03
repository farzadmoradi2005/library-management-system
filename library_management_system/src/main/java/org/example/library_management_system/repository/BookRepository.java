package org.example.library_management_system.repository;

import org.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findByAvailableAmountGreaterThan(int availableAmount);

    @Query("SELECT b FROM Book b WHERE b.availableAmount > 0 ORDER BY b.title")
    List<Book> findAvailableBooks();
    Optional<Book> findByIsbn(String isbn);
}
