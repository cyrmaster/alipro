package com.newland.aqhandle.Dao;

import com.newland.aqhandle.DTO.OrganizationOa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizationOaRepository extends JpaRepository<OrganizationOa,Integer> {
    OrganizationOa findByOrgCode(String orgCode);


}
