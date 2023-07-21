package com.cryptic.ypc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cryptic.ypc.dal.RegisteredUserRepository;
import com.cryptic.ypc.dal.UserRepository;
import com.cryptic.ypc.exceptions.BadRequestException;
import com.cryptic.ypc.model.user.GuestUser;
import com.cryptic.ypc.model.user.RegisteredUser;
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

	/** Saves a list of users
	 * @param users
	 */
	public void saveAll(List<User> users) {
		users.forEach(u -> this.save(u));
	}

	/**
	 * Saves a single user of any type If a userID is defined in user. Checks user
	 * data for correct format. If the user ID is taken by a registered user If
	 * taken by a guest user, and User to save is a registered user. Will Transform
	 * guest user into registered user
	 * 
	 * @param user User to save
	 * @return The ID of the user
	 */
	public long save(User user) {
		this.checkUserName(user.getUsername());

		User userFromDb = this.userRepository.findById(user.getId()).orElse(null);

		// Checks user from DB
		// If user from DB is a guess, they can become a registered user
		// Otherwise throw error
		if (userFromDb != null) {
			if (userFromDb.getClass() != GuestUser.class) {
				throw new BadRequestException("Cannot save a user with ID that already exsits");
			}

			if (user.getClass() != RegisteredUser.class) {
				throw new BadRequestException("Cannot save a user with ID that already exsits");
			}

			this.UpdateUserFromGuest((RegisteredUser) user);
			return user.getId();
		}

		// Save registered user
		if (user.getClass() == RegisteredUser.class) {
			return this.save((RegisteredUser) user);
		}

		logger.debug("Trying to save new guest user: " + user);
		this.userRepository.save(user);
		return user.getId();

	}

	/**
	 * Checks user password, encodes the password and saves the user
	 * 
	 * @param user User to save
	 * @return User ID
	 */
	private long save(RegisteredUser user) {
		this.checkUserPassword(user.getPassword());

		user.setPassword(encoder.encode(user.getPassword()));

		logger.debug("Trying to save new registered user: " + user);
		this.registeredUserRepository.save(user);
		return user.getId();
	}

	/**
	 * Updates a guest user to a registered user
	 * 
	 * @param user Registered user to overwrite a guest user
	 */
	private void UpdateUserFromGuest(RegisteredUser user) {
		this.checkUserPassword(user.getPassword());

		user.setPassword(encoder.encode(user.getPassword()));

		logger.trace("Updating guest user to registered user");

		this.userRepository.updateGuestUserToRegistered(user.getId(), user.getUsername(), user.getPassword());
	}

	/**
	 * Checks user name for correct format. Throws bad request with why it failed
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

	/**
	 * Checks user password for correct format. Throws bad request with why it
	 * failed
	 * 
	 * @param password User password
	 */
	private void checkUserPassword(String password) {
		int passwordLen = password.length();

		if (passwordLen < 4 || passwordLen >= 15) {
			logger.debug(String.format("Password failed to be correct length"));
			throw new BadRequestException("Password must be between 4 and 15 characters");
		}

		// Checks if password has any at lest one lower case letter
		if (!password.matches(".*[a-z]+.*")) {
			logger.debug(String.format("Password failed to contain a lower case letter"));
			throw new BadRequestException("Passwords must contain atlest one lower case letter");
		}

		// Checks if password has any at lest one capital letter
		if (!password.matches(".*[A-Z]+.*")) {
			logger.debug(String.format("Password failed to contain a capital letter"));
			throw new BadRequestException("Passwords must contain atlest one capital letter");
		}

		// Checks if password has any numbers
		if (!password.matches(".*\\d+.*")) {
			logger.debug(String.format("Password failed to contain a digit"));
			throw new BadRequestException("Passwords must contain atlest one digit");
		}

		// If password has trailing spaces
		if (password.length() != password.trim().length()) {
			logger.debug(String.format("Password failed has trailing spaces"));
			throw new BadRequestException("Passwords cannot start or end with a space");
		}
	}

}
