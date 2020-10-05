package com.example.newlibrary.domain;

import com.example.newlibrary.repository.BookRepository;
import com.example.newlibrary.repository.BorrowInfoRepository;
import com.example.newlibrary.repository.MemberRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@EntityListeners(SpringEntityListener.class)
@Slf4j
public class BorrowInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String bookNumber;


    private String memberNumber;

    Date date;



    public BorrowInfo(String bookNumber, String memberNumber, Date borrowDate) {
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
            log.info("book borrowed: "+ bookNumber);
        }else{
            log.info("not able to borrow the book!");
        }
    }
    public List<BorrowInfo> findBooksBorrowedByMember(String memberNumber){

        List<BorrowInfo> borrowInfoList=new ArrayList<BorrowInfo>();
        Iterable<BorrowInfo> booksIterable=borrowInfoRepository.findAllByMemberNumber(memberNumber);
//        bookList= StreamSupport.stream(booksIterable.spliterator(), false)
//                .collect(Collectors.toList());
        booksIterable.forEach(borrowInfoList::add);
        return borrowInfoList;
    }
}
