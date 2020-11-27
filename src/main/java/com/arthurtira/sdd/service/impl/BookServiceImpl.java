package com.arthurtira.sdd.service.impl;

import com.arthurtira.sdd.dto.BookDTO;
import com.arthurtira.sdd.mappers.BookMapper;
import com.arthurtira.sdd.models.Book;
import com.arthurtira.sdd.repository.BooksRepository;
import com.arthurtira.sdd.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BooksRepository booksRepository;

    public BookServiceImpl(BookMapper bookMapper,  BooksRepository booksRepository) {
        this.bookMapper = bookMapper;
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BookDTO> findBooks(String title) {
        List<Book> books;
        if("".equalsIgnoreCase(title) || title == null)
            books = convertIterable(this.booksRepository.findAll());
        else
            books = this.booksRepository.findBooksByBookTitleContains(title);

        return this.bookMapper.map(books);
    }

    private List<Book> convertIterable(Iterable<Book> bookIterable ){
        List<Book> books = new ArrayList<>();
        bookIterable.forEach(book -> books.add(book));
        return books;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = this.bookMapper.fromDTO(bookDTO);
        log.debug(" Book to be created {} " + book);
        book = this.booksRepository.save(book);
        return this.bookMapper.toDTO(book);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) {
        Optional<Book> optionalBook = this.booksRepository.findById(bookDTO.getBookId());
        Book newBook = this.bookMapper.fromDTO(bookDTO);
        if(optionalBook.isPresent()){
            Book existingBook = optionalBook.get();
            newBook.setBookId(existingBook.getBookId());
            this.booksRepository.save(newBook);
        }else {
            throw new IllegalArgumentException("Book not found");
        }
        return this.bookMapper.toDTO(newBook);
    }

    @Override
    public void deleteBook(String bookId) {
        Optional<Book> optionalBook = this.booksRepository.findById(bookId);
        if(optionalBook.isPresent()){
            this.booksRepository.delete(optionalBook.get());
        }else {
            throw new IllegalArgumentException("Books not found");
        }
    }

    @Override
    public Optional<BookDTO> getBookByBookId(String bookId) {
        Optional<Book> optionalBook =this.booksRepository.findById(bookId);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            return Optional.of(this.bookMapper.toDTO(book));
        }
        return Optional.empty();
    }
}
