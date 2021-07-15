package ch.zli.m223.ksh18a.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh18a.crm.exception.InvalidArgumentException;
import ch.zli.m223.ksh18a.crm.exception.UserAlreadyExistsException;
import ch.zli.m223.ksh18a.crm.exception.UserNotFoundException;
import ch.zli.m223.ksh18a.crm.model.AppUser;
import ch.zli.m223.ksh18a.crm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<AppUser> getAllUsers() {
		List<AppUser> res = new ArrayList<>();
		
		for (var user : userRepository.findAll()) {
			res.add(user);
		}
		return res;
	}

	@Override
	public AppUser addUser(String userName, String password, List<String> roles) {
		// Check parameter
		if (userName == null || password == null) {
			throw new InvalidArgumentException();
		}
		// Check already exists
		if (userRepository.findByUserName(userName).isPresent()) {
			throw new UserAlreadyExistsException();
		}
		return userRepository.insertUser(
				userName, password, roles == null ? new ArrayList<>() : roles);
	}

	@Override
	public AppUser findByUserName(String userName) {
		if (userName == null) {
			throw new InvalidArgumentException();
		}
		return userRepository.findByUserName(userName)
			.orElseThrow(UserNotFoundException::new);
	}

	@Override
	public AppUser getUserById(long userId) {		
		return userRepository.findById(userId)
				.orElseThrow(UserNotFoundException::new);
	}

	@Override
	public void deleteUserById(long userId) {
		getUserById(userId);
		userRepository.deleteById(userId);
	}

	@Override
	public AppUser setRolesForUser(long userId, List<String> roles) {
		if (roles == null) { throw new InvalidArgumentException(); }
		AppUser user = getUserById(userId);
		return userRepository.setRoles(user, roles);
	}	
}
