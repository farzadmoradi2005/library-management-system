package org.example.library_management_system.Mapper;

import org.example.library_management_system.dto.BookResponseDTO;
import org.example.library_management_system.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponseDTO bookToBookResponseDTO(Book book);
    Book bookResponseDTOToBook(BookResponseDTO bookResponseDTO);
}
