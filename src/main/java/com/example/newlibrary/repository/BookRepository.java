package com.example.newlibrary.repository;

import com.example.newlibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface BookRepository extends CrudRepository<Book,String> {

    Optional<Book> findByNumber(String bookNumber);
}
