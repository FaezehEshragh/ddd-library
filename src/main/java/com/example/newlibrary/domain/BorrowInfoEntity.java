package com.example.newlibrary.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
//@EntityListeners(SpringEntityListener.class)
@Slf4j
public class BorrowInfoEntity extends BorrowInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String bookNumber;


    private String memberNumber;

    Date date;

    public BorrowInfoEntity() {
//        borrowInfoRepository=
    }

    public BorrowInfoEntity(String bookNumber, String memberNumber, Date borrowDate) {
        setBookNumber(bookNumber);
        setMemberNumber(memberNumber);
        setDate(borrowDate);
    }

    public void addInfo(Book book, Member member, Date borrowDate) {
        setDate(borrowDate);
        if(book.isAvailable() && member.canBorrow() && findBooksBorrowedByMember(memberNumber).size()<member.getAllowed()){
            setBookNumber(book.getNumber());
            setMemberNumber(member.getNumber());
            book.setAvailable(false);
            bookRepository.save(book);
            borrowInfoRepository.save(this);
            log.info("book borrowed: "+ bookNumber);
        }else{
            log.info("not able to borrow the book!");
        }
    }

    public List<BorrowInfoEntity> findBooksBorrowedByMember(String memberNumber){

        List<BorrowInfoEntity> borrowInfoEntityList =new ArrayList<BorrowInfoEntity>();
        System.out.println("repo ID:"+getBorrowInfoRepository().toString());
        Iterable<BorrowInfoEntity> booksIterable=getBorrowInfoRepository().findAllByMemberNumber(memberNumber);
//        bookList= StreamSupport.stream(booksIterable.spliterator(), false)
//                .collect(Collectors.toList());
        booksIterable.forEach(borrowInfoEntityList::add);
        return borrowInfoEntityList;
    }
}
