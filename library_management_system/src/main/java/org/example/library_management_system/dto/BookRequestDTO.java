package org.example.library_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.library_management_system.validation.UniqueIsbn;
import org.example.library_management_system.validation.ValidationGroups;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "عنوان کتاب الزامی است")
    @Size(min = 2, max = 255, message = "عنوان باید بین ۲ تا ۲۵۵ کاراکتر باشد")
    private String title;


    @Min(value = 1, message = "حداقل یک نسخه باید وجود داشته باشد")
    @Max(value = 100, message = "حداکثر ۱۰۰ نسخه مجاز است")
    private int totalCopies;

    @UniqueIsbn(groups = ValidationGroups.OnCreate.class)
    @Pattern(regexp = "...", message = "فرمت ISBN معتبر نیست")
    private String isbn;
}