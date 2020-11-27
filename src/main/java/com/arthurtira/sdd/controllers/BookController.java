package com.arthurtira.sdd.controllers;

import com.arthurtira.sdd.dto.ApiResponse;
import com.arthurtira.sdd.dto.BookConstants;
import com.arthurtira.sdd.dto.BookDTO;
import com.arthurtira.sdd.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ApiResponse findBooks(@RequestParam(defaultValue = "") String title) {
        return successResponse(this.bookService.findBooks(title), BookConstants.MULTIPLE_BOOKS_SUCCESS_MASSEAGE);
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = this.bookService.saveBook(bookDTO);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") String bookId) {
        try {
            this.bookService.deleteBook(bookId);
        }catch(Exception ex){

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BookDTO> updateBook(@RequestBody  BookDTO bookDTO) {
        return new ResponseEntity<>(this.bookService.updateBook(bookDTO), HttpStatus.OK);
    }

    private <T> ApiResponse<T> successResponse(T payload, String message) {
        return new ApiResponse<>(message, new LinkedList<>(), true, payload);
    }


}
