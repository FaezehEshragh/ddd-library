package com.example.newlibrary.service;

import com.example.newlibrary.domain.Book;
import com.example.newlibrary.domain.BorrowInfoEntity;
import com.example.newlibrary.domain.Member;
import com.example.newlibrary.service.dto.BorrowInfoDTO;
import com.example.newlibrary.service.exception.BookNotFoundException;
import com.example.newlibrary.repository.BookRepository;
import com.example.newlibrary.repository.BorrowInfoRepository;
import com.example.newlibrary.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public BorrowInfoEntity borrowBook(BorrowInfoDTO borrowInfoDTO) {
        String bookNumber=borrowInfoDTO.getBookNumber();
        Book book= bookRepository.findByNumber(bookNumber)
                .orElseThrow(() -> new BookNotFoundException(bookNumber));
        String memberNumber=borrowInfoDTO.getMemberNumber();
        Member member=memberRepository.findByNumber(memberNumber)
                .orElseThrow(() -> new BookNotFoundException(memberNumber));
        BorrowInfoEntity borrowInfoEntity =new BorrowInfoEntity();
        borrowInfoEntity.addInfo(book,member,borrowInfoDTO.getBorrowDate());

//        if(borrowInfoEntity.getBookNumber()!=null) {
////            bookRepository.save(book);
////            member.getBorrowedBooks().add(book);
////            memberRepository.save(member);
//            borrowInfoRepository.save(borrowInfoEntity);
//        }
        return borrowInfoEntity;
     }


}
