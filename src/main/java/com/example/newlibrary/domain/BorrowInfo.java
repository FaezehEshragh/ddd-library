package com.example.newlibrary.domain;

import com.example.newlibrary.repository.BorrowInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public  class BorrowInfo {

    static BorrowInfoRepository borrowInfoRepository;
    @Autowired
    private void setBorrowInfoRepository( BorrowInfoRepository borrowInfoRepository){
        this.borrowInfoRepository=borrowInfoRepository;
    }

    public BorrowInfoRepository getBorrowInfoRepository() {
        return borrowInfoRepository;
    }


}
