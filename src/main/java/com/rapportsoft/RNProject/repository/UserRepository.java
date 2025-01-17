package com.rapportsoft.RNProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.rapportsoft.RNProject.entities.AppUser;
import com.rapportsoft.RNProject.entities.UserId;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<AppUser, UserId> {
	
	@Query(value="select * from appuserinfo as u where u.user_id=:uid", nativeQuery=true)
    public AppUser findByUser_Id(@Param("uid") String userId);
	
	
	
	

}
