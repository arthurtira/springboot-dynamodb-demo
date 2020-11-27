package com.arthurtira.sdd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {
    private String bookId;
    private String title;
    private int releaseYear;
    private boolean available;
    private String isbn;
    private String description;
}
