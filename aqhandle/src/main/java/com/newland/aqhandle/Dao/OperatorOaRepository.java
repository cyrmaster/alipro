package com.newland.aqhandle.Dao;

import com.newland.aqhandle.DTO.OperatorOa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperatorOaRepository extends JpaRepository<OperatorOa,Long> {
    //获取oracle序列的下一个值，方法返回类型是Long或者String都可正常运行
    @Query(value = "select seq_operatorid.nextval from dual", nativeQuery = true)
    String getNextval();
}
