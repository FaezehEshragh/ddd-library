package com.example.newlibrary.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
//@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String number;
    private String name;
    private String author;
    private boolean available;

    public Book(String number, String name, String author, boolean available) {
        setNumber(number);
        setName(name);
        setAuthor(author);
        setAvailable(available);
    }

    public boolean isAvailable(){
        return available;
    }
}
