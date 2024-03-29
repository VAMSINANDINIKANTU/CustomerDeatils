package com.customerapp.web.controller;

import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.customerapp.model.persistance.User;
import com.customerapp.model.persistance.UserNotFoundException;
import com.customerapp.model.service.UserService;

@Controller
public class UserMgtController {
	@Autowired
	private UserService userService;

	// @PostConstruct
	public void init() {
		userService.addUser(new User("nandu", "nandu@gmail.com", "nandu123", "admin", true));
		userService.addUser(new User("venky", "venky@gmail.com", "venky123", "mgr", true));
		userService.addUser(new User("gunika", "gunika@raj.com", "gunika123", "emp", true));
	}

	@RequestMapping(value = "login")
	public String loginGet(HttpServletRequest req, ModelMap map) {
		map.addAttribute("userbean", new UserFormBean());
		map.addAttribute("error", req.getParameter("error"));
		return "login";

	}

	@RequestMapping(value = "logout")
	public String logoutGet(ModelMap map) {
		map.addAttribute("userbean", new UserFormBean());
		map.addAttribute("message", "you arew succesfully logout");
		return "login";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest req, @Valid @ModelAttribute(value = "userbean") UserFormBean userBean,
			BindingResult bindingResult) {
		User user = null;

		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			try {

				user = userService.getUser(userBean.getEmail(), userBean.getPassword());
			} catch (UserNotFoundException e) {
				return "redirect:login?error=login failed";
			}
			HttpSession httpSession = req.getSession();
			httpSession.setAttribute("user", user);
		}
		return "redirect:allcustomers";
	}
}