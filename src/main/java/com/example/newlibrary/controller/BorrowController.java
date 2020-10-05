package com.example.newlibrary.controller;


import com.example.newlibrary.service.dto.BorrowInfoDTO;
import com.example.newlibrary.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/borrow")
public class BorrowController {

    BorrowService borrowService;

    @Autowired
    private void setBorrowService( BorrowService borrowService){
        this.borrowService=borrowService;
    }


    @GetMapping ()
    public String loadBorrowPage(Model model){
        model.addAttribute("borrowInfoDto", new BorrowInfoDTO());
        return "borrow";
    }


    @PostMapping ()
    public String borrowBook(@ModelAttribute("borrowInfoDto") BorrowInfoDTO borrowInfoDTO){
        borrowService.borrowBook(borrowInfoDTO);


        return "redirect:/";
    }



}
