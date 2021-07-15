package ch.zli.m223.ksh18a.crm.service;

import java.util.List;

import ch.zli.m223.ksh18a.crm.model.AppUser;


public interface UserService {

	List<AppUser> getAllUsers();

	AppUser addUser(String userName, String password, List<String> roles);
	
	AppUser findByUserName(String userName);

	AppUser getUserById(long userId);

	void deleteUserById(long userId);

	AppUser setRolesForUser(long userId, List<String> roles);

}
