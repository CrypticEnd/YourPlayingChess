package com.cryptic.ypc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cryptic.ypc.model.user.GuestUser;
import com.cryptic.ypc.model.user.RegisteredUser;
import com.cryptic.ypc.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/registered")
	@ResponseStatus(HttpStatus.CREATED)
	public long saveNewRegisteredUser(@RequestBody RegisteredUser user) {
		return this.userService.save(user);
	}

	@PostMapping("/guest")
	@ResponseStatus(HttpStatus.CREATED)
	public long saveNewGuestUser(@RequestBody GuestUser user) {
		return this.userService.save(user);
	}

	@PutMapping("/registered")
	public void updateRegisteredUser(@RequestBody RegisteredUser user, Authentication auth) {
		this.userService.updateUser(user, auth);
	}

	@PutMapping("/guest")
	public void updateGussUser(@RequestBody GuestUser user) {
		this.userService.updateUser(user);
	}

}
