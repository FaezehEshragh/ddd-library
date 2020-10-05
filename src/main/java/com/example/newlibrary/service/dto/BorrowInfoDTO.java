package com.example.newlibrary.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowInfoDTO {
    String bookNumber;
    String memberNumber;
    Date borrowDate;
}
