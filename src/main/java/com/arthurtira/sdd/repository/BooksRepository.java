package com.arthurtira.sdd.repository;

import com.arthurtira.sdd.models.Book;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@EnableScan
@EnableScanCount
public interface BooksRepository extends PagingAndSortingRepository<Book, String> {
    List<Book> findBooksByBookTitleContains(String title);


}
