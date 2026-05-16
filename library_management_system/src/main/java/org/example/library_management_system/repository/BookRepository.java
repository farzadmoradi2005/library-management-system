package org.example.library_management_system.repository;

import org.example.library_management_system.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTitleContainsIgnoreCase(String title , Pageable pageable);

    Page<Book> findByAvailableAmountGreaterThan(int availableAmount , Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.availableAmount > 0 ORDER BY b.title")
    Page<Book> findAvailableBooks(Pageable pageable);
    @Modifying
    @Query("UPDATE Book b SET b.availableAmount = b.availableAmount - 1 " +
            "WHERE b.id = :id AND b.availableAmount > 0")
    int decreaseAvailableAmount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Book b SET b.availableAmount = b.availableAmount + 1 " +
            "WHERE b.id = :id")
    void increaseAvailableAmount(@Param("id") Long id);


    Optional<Book> findByIsbn(String isbn);
}
