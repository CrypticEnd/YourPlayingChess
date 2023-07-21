package com.cryptic.ypc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cryptic.ypc.dal.RegisteredUserRepository;
import com.cryptic.ypc.dal.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
}
