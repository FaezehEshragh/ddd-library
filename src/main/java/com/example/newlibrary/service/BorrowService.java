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
//        borrowInfo.addInfo(book,member,borrowInfoDTO.getBorrowDate());
        if(book.isAvailable() && member.canBorrow() && findBooksBorrowedByMember(memberNumber).size()<member.getAllowed()){
            borrowInfo.setBookNumber(book.getNumber());
            borrowInfo.setMemberNumber(member.getNumber());
            book.setAvailable(false);
            bookRepository.save(book);
//            member.getBorrowedBooks().add(book);
//            memberRepository.save(member);
            borrowInfoRepository.save(borrowInfo);
            log.info("book borrowed: "+ bookNumber);
        }else{
            log.info("not able to borrow the book!");
        }
        return borrowInfo;
     }
    public List<BorrowInfo> findBooksBorrowedByMember(String memberNumber){

        List<BorrowInfo> borrowInfoList=new ArrayList<BorrowInfo>();
        System.out.println("Repo obj ID:"+borrowInfoRepository.toString());
        Iterable<BorrowInfo> booksIterable=borrowInfoRepository.findAllByMemberNumber(memberNumber);
//        bookList= StreamSupport.stream(booksIterable.spliterator(), false)
//                .collect(Collectors.toList());
        booksIterable.forEach(borrowInfoList::add);
        return borrowInfoList;
    }

}
