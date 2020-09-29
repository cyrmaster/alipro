package com.newland.aqhandle.Dao;

import com.newland.aqhandle.DTO.AqHisBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AqHisBackRepository extends JpaRepository<AqHisBack,Integer> {
    @Query(value = "select seq_aqhisback.nextval from dual", nativeQuery = true)
    String getNextval();

    AqHisBack findByUSRKEY(String USRKEY);
}
