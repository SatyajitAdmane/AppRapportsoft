package com.rapportsoft.RNProject.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rapportsoft.RNProject.Dtos.AppIgmDto;
import com.rapportsoft.RNProject.entities.Appcfigmcn;
import com.rapportsoft.RNProject.entities.CfigmcnId;

import jakarta.persistence.EntityManager;

import java.util.*;

@Repository 
public interface AppIgmRepository extends JpaRepository<Appcfigmcn,CfigmcnId >{
	
	
	
	@Query(value="select igm_no,igm_trans_id,container_no,container_size,container_type from appcfigmcn "
			+ "where branch_id=:bid and company_id=:cid and fin_year='2024' and "
			+ "container_no =:containerNo and (gate_in_id='' or gate_in_id is null) and (gate_out_id ='' or  gate_out_id is null)",nativeQuery = true)
	List<Object[]> searchByContainerNo(@Param("cid") String cid,@Param("bid") String bid,@Param("containerNo") String containerNo);

}
