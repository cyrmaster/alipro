package com.newland.aqhandle.Dao;

import com.newland.aqhandle.DTO.OrganizationVirtualOa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizationVirtualOaRepository extends JpaRepository<OrganizationVirtualOa,Integer> {
    @Query(value = "select seq_orgid_virtual.nextval from dual", nativeQuery = true)
    String getNextval();

    OrganizationVirtualOa findByOrgCode(String OrgCode);

     int deleteByBackOrgId(Integer backOrgId);
}
