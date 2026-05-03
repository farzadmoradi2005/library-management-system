// src/main/java/com/library/dto/UserRequest.java
package org.example.library_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRequestDTO {

    @NotBlank(message = "نام الزامی است")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "ایمیل الزامی است")
    @Email(message = "فرمت ایمیل معتبر نیست")
    private String email;

    @NotBlank(message = "رمز عبور الزامی است")
    @Size(min = 8, message = "رمز عبور باید حداقل ۸ کاراکتر باشد")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "رمز عبور باید شامل حرف بزرگ، حرف کوچک و عدد باشد"
    )
    private String password;
}