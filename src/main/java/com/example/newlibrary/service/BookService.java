package com.example.newlibrary.service;

import com.example.newlibrary.domain.Book;
import com.example.newlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }




    public List<Book> getAllBooks() {
        List<Book> bookList=new ArrayList<Book>();
        Iterable<Book> booksIterable=bookRepository.findAll();
        booksIterable.forEach(bookList::add);
        return bookList;    }
}
