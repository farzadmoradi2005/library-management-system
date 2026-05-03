// src/main/java/com/library/validation/UniqueIsbnValidator.java
package org.example.library_management_system.validation;

import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.example.library_management_system.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueIsbnValidator implements ConstraintValidator<UniqueIsbn, String> {

    private final BookRepository bookRepository;

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext context) {
        // اگه isbn خالی بود رد نکن — @NotBlank این کار رو میکنه
        if (isbn == null || isbn.isBlank()) return true;
        return !bookRepository.findByIsbn(isbn).isPresent();
    }
}