package com.main.service;

import com.main.binding.LoginForm;
import com.main.binding.SignupForm;
import com.main.binding.UnlockForm;

public interface UserService {
	
	public String login(LoginForm login);
	
	public boolean signup(SignupForm sign);
	
	public boolean unlock(UnlockForm unlock);
	
	public boolean forgot(String email);
	

}
