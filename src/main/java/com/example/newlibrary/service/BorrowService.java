package com.example.newlibrary.service;

import com.example.newlibrary.domain.Book;
import com.example.newlibrary.domain.BorrowInfo;
import com.example.newlibrary.domain.Member;
import com.example.newlibrary.service.dto.BorrowInfoDTO;
import com.example.newlibrary.service.exception.BookNotFoundException;
import com.example.newlibrary.repository.BookRepository;
import com.example.newlibrary.repository.BorrowInfoRepository;
import com.example.newlibrary.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BorrowService {



    BookRepository bookRepository;
    @Autowired
    private void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    MemberRepository memberRepository;
    @Autowired
    private void setBookRepository( BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    BorrowInfoRepository borrowInfoRepository;
    @Autowired
    private void setBorrowInfoRepository( BorrowInfoRepository borrowInfoRepository){
        this.borrowInfoRepository=borrowInfoRepository;
    }


    public BorrowInfo borrowBook(BorrowInfoDTO borrowInfoDTO) {
        String bookNumber=borrowInfoDTO.getBookNumber();
        Book book= bookRepository.findByNumber(bookNumber)
                .orElseThrow(() -> new BookNotFoundException(bookNumber));
        String memberNumber=borrowInfoDTO.getMemberNumber();
        Member member=memberRepository.findByNumber(memberNumber)
                .orElseThrow(() -> new BookNotFoundException(memberNumber));
        BorrowInfo borrowInfo=new BorrowInfo();
        borrowInfo.addInfo(book,member,borrowInfoDTO.getBorrowDate());
        if(borrowInfo.getBookNumber()!=null) {
//            bookRepository.save(book);
//            member.getBorrowedBooks().add(book);
//            memberRepository.save(member);
            borrowInfoRepository.save(borrowInfo);
        }
        return borrowInfo;
     }


}
