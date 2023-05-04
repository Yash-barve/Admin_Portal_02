package com.main.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
public class StudentDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Studentdd;
	private String studentname;
	private String studentnumber;
	private String studentcmode;
	private String studentstatus;
	
	@CreationTimestamp
	private LocalDate studentcrdate;
	
	@UpdateTimestamp
	private LocalDate studentupdate;
	
//	@OneToMany(mappedBy = "" ,cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
//	private UserDtlsEntity userId;



	
	
	
	

}
