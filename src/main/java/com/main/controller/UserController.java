package com.main.controller;

import com.main.binding.UnlockForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.binding.LoginForm;
import com.main.binding.SignupForm;
import com.main.service.UserService;
import com.main.service.UserServiceImpl;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;

	@GetMapping("/login")
	public String loginUser(Model model) {
		model.addAttribute("loginform", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String loginUs(@ModelAttribute("loginform") LoginForm loginForm, Model model) {

		String status = userservice.login(loginForm);

		if (status.contains("success")) {

			return "redirect:/dashboard";
		}
		model.addAttribute("errmsg", status);
		return "login";
	}

	@GetMapping("/signup")
	public String creatUser(Model model) {
		model.addAttribute("user", new SignupForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSignup(@ModelAttribute("user") SignupForm sign, Model model) {

		boolean status = userservice.signup(sign);

		if (status) {
			model.addAttribute("successMsg", "check your Email");
		} else {
			model.addAttribute("errMsg", "choose unique Email");
		}
		return "signup";
	}

	@GetMapping("/forgot")
	public String forgotP() {
		return "forgotpwd";
	}

	@PostMapping("/handleforgot")
	public String forgotPage(@RequestParam("email") String email, Model model) {
		
		System.out.println(email);
		
		boolean status = userservice.forgot(email);

		if (status) {
			model.addAttribute("succmsg", "Password Sent your gmail");
		} else {
			model.addAttribute("errmsg", "Invalid email");
		}

		return "forgotpwd";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {

		UnlockForm unlockFormobj = new UnlockForm();
		unlockFormobj.setEmail(email);
		model.addAttribute("unlock", unlockFormobj);

		return "unlock";
	}

	@PostMapping("/handleunlock")
	public String unlockp(@ModelAttribute("unlock") UnlockForm unlock, Model model) {

		if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userservice.unlock(unlock);

			if (status) {
				model.addAttribute("succmsg", "Your Accont unlocked");
			} else {
				model.addAttribute("errmsg", "Given temporary password is Incorrect check your gmail");
			}
		} else {
			model.addAttribute("errmsg", "password and confirm password are not same");
		}

		return "unlock";
	}

}
