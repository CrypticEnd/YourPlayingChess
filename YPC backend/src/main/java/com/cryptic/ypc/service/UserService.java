package com.cryptic.ypc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cryptic.ypc.dal.RegisteredUserRepository;
import com.cryptic.ypc.dal.UserRepository;
import com.cryptic.ypc.exceptions.BadRequestException;
import com.cryptic.ypc.model.user.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	

	/**
	 * Checks user name for correct format. Auto Trims username as trailing blank
	 * spaces are not allowed Throws bad request with why it failed
	 * 
	 * @param username user name to check
	 */
	private void checkUserName(String username) {
		username = username.trim();
		int nameLen = username.length();

		if (nameLen < 4 || nameLen >= 15) {
			logger.debug(String.format("Username: '%s' failed to be correct length", username));
			throw new BadRequestException("User name must be between 4 and 15 characters");
		}

		// Checks if user name is only letters dashes and underscores
		if (!username.matches("[a-zA-Z0-9\\-\\_]+")) {
			logger.debug(String.format("Username: '%s', contains not allowed chars", username));
			throw new BadRequestException("User name can only contain letters, dashes [-] or underscores [_]");
		}
	}

}
