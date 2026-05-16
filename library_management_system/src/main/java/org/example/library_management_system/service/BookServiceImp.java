package org.example.library_management_system.service;

import lombok.RequiredArgsConstructor;
import org.example.library_management_system.Mapper.BookMapper;
import org.example.library_management_system.dto.BookRequestDTO;
import org.example.library_management_system.dto.BookResponseDTO;
import org.example.library_management_system.dto.PageResponseDTO;
import org.example.library_management_system.exception.ResourceNotFoundException;
import org.example.library_management_system.model.Book;
import org.example.library_management_system.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
   private final BookRepository bookRepository;
   private final BookMapper bookMapper;
   @Override
   public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
       Book book = Book.builder()
               .title(bookRequestDTO.getTitle())
               .isbn(bookRequestDTO.getIsbn())
               .totalCopies(bookRequestDTO.getTotalCopies())
               .availableAmount(bookRequestDTO.getTotalCopies())
               .build();
       //System.out.println("aaa");
       return bookMapper.bookToBookResponseDTO(bookRepository.save(book));
   }
   @Override
   public PageResponseDTO<BookResponseDTO> getAllBooks(Pageable pageable) {
       Page<Book> books = bookRepository.findAll(pageable);
       Page<BookResponseDTO> bookResponseDTOS = books.map(bookMapper::bookToBookResponseDTO);
       return PageResponseDTO.of(bookResponseDTOS);
   }
   @Override
   public BookResponseDTO getBookById(Long id) {
       Book book = getBookOrThrow(id);
       return bookMapper.bookToBookResponseDTO(book);
   }
   @Override
   public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
       Book book = getBookOrThrow(id);
       book.setTitle(bookRequestDTO.getTitle());
       book.setTotalCopies(bookRequestDTO.getTotalCopies());
       book.setIsbn(bookRequestDTO.getIsbn());
       return bookMapper.bookToBookResponseDTO(bookRepository.save(book));
   }
   @Override
   public void deleteBook(Long id) {
       if (!bookRepository.existsById(id)) {
           throw new ResourceNotFoundException("Book not found");
       }
       bookRepository.deleteById(id);
   }
   @Override
   public PageResponseDTO<BookResponseDTO> searchBook(String title , Pageable pageable) {
       Page<Book> page = bookRepository.findByTitleContainsIgnoreCase(title , pageable);
       Page<BookResponseDTO> pageResponseDTO = page.map(bookMapper::bookToBookResponseDTO);
       return PageResponseDTO.of(pageResponseDTO);
   }
   private Book getBookOrThrow(Long id) {
       return bookRepository.findById(id)
               .orElseThrow(() ->
                       new ResourceNotFoundException("Book with id" + id + "was not found")

               );
   }
}
