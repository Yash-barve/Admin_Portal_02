package com.main.service;

import java.net.IDN;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.binding.LoginForm;
import com.main.binding.SignupForm;
import com.main.binding.UnlockForm;
import com.main.entity.UserDtlsEntity;
import com.main.repo.UserDtlsRepo;
import com.main.utils.EmailUtils;
import com.main.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo urepo;

	@Autowired
	private EmailUtils emailutils;

	@Override
	public boolean signup(SignupForm sign) {

		urepo.deleteAll();
		UserDtlsEntity user = urepo.findByEmail(sign.getEmail());
		if (user != null) {
			return false;
		}

		UserDtlsEntity entity = new UserDtlsEntity();
		BeanUtils.copyProperties(sign, entity);

		String tempPwd = PwdUtils.generatePwd();
		entity.setPassword(tempPwd);

		entity.setStatus("LOCKED");

		urepo.save(entity);

		String to = sign.getEmail();
		String subject = "Unlock your Account";
		StringBuffer body = new StringBuffer("");

		body.append("<h1> this your temporary password below link please unlock your Account. </h1>");
		body.append("Tempory Password  :- " + "<b>" + tempPwd + "</b>");
		body.append("<br>");
		body.append("<br>");
		body.append("<a href=\"http://localhost:9091/unlock?email=" + to + "\">Click here to unlock your Account</a>");

		emailutils.sendMailPwd(subject, body.toString(), to);
		return true;
	}

	@Override
	public boolean unlock(UnlockForm unlock) {

		UserDtlsEntity entity = urepo.findByEmail(unlock.getEmail());

		if (entity.getPassword().equals(unlock.getTempPwd())) {
			entity.setPassword(unlock.getNewPwd());
			entity.setStatus("UNLOCKED");
			urepo.save(entity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String login(LoginForm login) {

		UserDtlsEntity entity = urepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (entity == null) {
			return "Invalid Credentials";
		}

		if (entity.getStatus().equals("LOCKED")) {
			return "Your Account is Locked please Unlock your Account";
		}

		return "success";
	}

	@Override
	public boolean forgot(String email) {

		UserDtlsEntity entity = urepo.findByEmail(email);

		if (entity == null) {
			return false;
		}

		String subject = "Recover Password";
		String body = "your password is ".trim() + entity.getPassword();

		emailutils.sendMailPwd(subject, body, email);

		return true;
	}

}
