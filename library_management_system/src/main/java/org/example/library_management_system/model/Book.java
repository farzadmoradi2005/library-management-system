package org.example.library_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isAvailable;
    @Column(nullable = false)
    private int availableAmount;
    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Loan> loans;
}
