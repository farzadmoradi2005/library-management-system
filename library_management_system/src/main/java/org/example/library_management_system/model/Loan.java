package org.example.library_management_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    private Book book;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;
    private LocalDate returnDate;
}
