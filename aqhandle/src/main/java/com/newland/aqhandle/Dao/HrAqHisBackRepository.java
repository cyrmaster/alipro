package com.newland.aqhandle.Dao;

import com.newland.aqhandle.DTO.HrAqHisBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HrAqHisBackRepository extends JpaRepository<HrAqHisBack,String> {
    @Query(value = "select seq_hr_aq_seq.nextval from dual", nativeQuery = true)
    String getNextval();
}
