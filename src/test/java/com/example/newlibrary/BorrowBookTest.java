package com.example.newlibrary;

import com.example.newlibrary.domain.Book;
import com.example.newlibrary.domain.BorrowInfoEntity;
import com.example.newlibrary.domain.Member;
import com.example.newlibrary.service.dto.BorrowInfoDTO;
import com.example.newlibrary.repository.BookRepository;
import com.example.newlibrary.repository.BorrowInfoRepository;
import com.example.newlibrary.repository.MemberRepository;
import com.example.newlibrary.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowBookTest {

  @Mock
    BookRepository bookRepository;
   @Mock
    MemberRepository memberRepository;
    @Mock
    BorrowInfoRepository borrowInfoRepository;
   @InjectMocks
    BorrowService borrowService;
    @Test
    public void testBorrowBook(){
        //setup
        //<editor-fold...>
        BorrowInfoDTO borrowInfoDTO=new BorrowInfoDTO();
        borrowInfoDTO.setBookNumber("testBook111");
        borrowInfoDTO.setMemberNumber("testMember111");
        borrowInfoDTO.setBorrowDate(Calendar.getInstance().getTime());


        String bookNumber=borrowInfoDTO.getBookNumber();
        Book mockBook=new Book(bookNumber,"TestBook", "TestAuthor", true);
        String memberNumber=borrowInfoDTO.getMemberNumber();
        Member mockMember=new Member(memberNumber, "TestMember", false, 3,0);


        when(bookRepository.findByNumber("testBook111")).thenReturn(java.util.Optional.of(mockBook));
        when(memberRepository.findByNumber("testMember111")).thenReturn(java.util.Optional.of(mockMember));
        //</editor-fold...>
        //execute
        BorrowInfoEntity borrowInfoEntity =borrowService.borrowBook(borrowInfoDTO);

        //verify
        assertEquals(false,mockBook.isAvailable());
        assertEquals("testBook111", borrowInfoEntity.getBookNumber());
        assertEquals("testMember111", borrowInfoEntity.getMemberNumber());

    }


}
