package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.main.entity.UserDtlsEntity;


@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {
	
	 public UserDtlsEntity findByEmail(String email);
	 
	 public UserDtlsEntity findByEmailAndPassword(String email ,String password);

}
