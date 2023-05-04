package com.main.service;

import java.util.List;

import com.main.binding.EnqSearchCritria;
import com.main.binding.EnqiryForm;
import com.main.entity.StudentDtlsEntity;

public interface EnquiryService {

	public List<String> getCourseNames();

	public List<String> getEnquiryStatus();
	
	public String addEnquiry(EnqiryForm enqiryForm);
	
	public String viewEnquiry(Integer userid , EnqSearchCritria search);
	
	public String dashboard(Integer userid);
	
	public EnqiryForm update(Integer userId);
	
	


	

}
