package com.example.newlibrary.repository;

import com.example.newlibrary.domain.BorrowInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface BorrowInfoRepository extends CrudRepository<BorrowInfo,String> {


    Collection<BorrowInfo> findAllByMemberNumber(String memberNumber);
}
