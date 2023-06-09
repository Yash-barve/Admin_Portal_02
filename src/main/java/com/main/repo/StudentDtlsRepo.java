package com.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.StudentDtlsEntity;

@Repository
public interface StudentDtlsRepo extends JpaRepository<StudentDtlsEntity, Integer> {

	@Query("select distinct(studentcmode) from StudentDtlsEntity")
	public List<String> getCourseNames();

	@Query("select distinct(studentstatus) from StudentDtlsEntity")
	public List<String> getEnquiryStatus();

}
