package com.arthurtira.sdd.service;

import com.arthurtira.sdd.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> findBooks(String title);
    BookDTO saveBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO);
    void deleteBook(String bookId);
    Optional<BookDTO> getBookByBookId(String bookId);

}
