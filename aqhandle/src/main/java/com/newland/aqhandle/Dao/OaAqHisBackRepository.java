package com.newland.aqhandle.Dao;

import com.newland.aqhandle.DTO.OaAqHisBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OaAqHisBackRepository extends JpaRepository<OaAqHisBack,String> {
    @Query(value = "select seq_oa_aq_seq.nextval from dual", nativeQuery = true)
    String getNextval();

    OaAqHisBack findByORGKEY (String ORGKEY);
}
