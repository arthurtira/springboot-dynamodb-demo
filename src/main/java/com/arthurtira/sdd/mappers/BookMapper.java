package com.arthurtira.sdd.mappers;

import com.arthurtira.sdd.dto.BookDTO;
import com.arthurtira.sdd.models.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public Book fromDTO(BookDTO bookDTO) {
        return Book.builder()
                .bookId(bookDTO.getBookId())
                .bookTitle(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .isbn(bookDTO.getIsbn())
                .releaseYear(bookDTO.getReleaseYear())
                .available(bookDTO.isAvailable() ? "YES" : "NO")
                .build();
    }

    public BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .bookId(book.getBookId())
                .title(book.getBookTitle())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .releaseYear(book.getReleaseYear())
                .available(book.getAvailable().equals("YES") ? true : false)
                .build();
    }

    public List<BookDTO> map(List<Book> bookList) {
        return bookList
                .parallelStream()
                .map(book -> toDTO(book))
                .collect(Collectors.toList());
    }


}
