package com.example.newlibrary.repository;

import com.example.newlibrary.domain.BorrowInfoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface BorrowInfoRepository extends CrudRepository<BorrowInfoEntity,String> {


    Collection<BorrowInfoEntity> findAllByMemberNumber(String memberNumber);
}
