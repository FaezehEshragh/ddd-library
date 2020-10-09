package com.example.newlibrary.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    private String number;
    private String name;
    private Boolean blocked;
    private Integer  allowed;
    private Integer  numberOfBorrowedBooks=0;

    public Member(String memberNumber, String name, boolean blocked, int numberOfAllowedBooks, int numberOfBorrowedBooks) {
        setNumber(memberNumber);
        setName(name);
        setBlocked(blocked);
        setAllowed(numberOfAllowedBooks);
        setNumberOfBorrowedBooks(numberOfBorrowedBooks);
    }


// cause book can exist without a borrower I didn't use ManyToOne in book class
//    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    List<Book> borrowedBooks;



    public boolean canBorrow(){
        return !blocked ;
//        && borrowedBooks.size()<numberOfAllowedBooks;
    }

//    public List<Book> findAllBooks(){
//        List<Book> books=new ArrayList<>();
//
//
//    }


}
