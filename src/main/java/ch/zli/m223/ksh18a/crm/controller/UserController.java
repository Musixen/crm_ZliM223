package ch.zli.m223.ksh18a.crm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.ksh18a.crm.model.AppUser;
import ch.zli.m223.ksh18a.crm.service.UserService;

@RestController()
@RequestMapping("rest/v1/users")
public class UserController {
	@Autowired
	private UserService userService;
		
	@GetMapping("")
	List<UserDto> getAllUsers() {
		return userService.getAllUsers()
			.stream()
			.map(user -> new UserDto(user))
			.collect(Collectors.toList());
	}
	
	@PostMapping("")
	UserDto insertUser(@RequestBody  UserInputDto userInput) {
		return new UserDto(
				userService.addUser(
						userInput.userName, 
						userInput.password, 
						userInput.roles)
			);
	}
	
	@GetMapping("/{id}")
	UserDto getUserById(@PathVariable("id") long userId) {
		return new UserDto(userService.getUserById(userId));
	}

	@DeleteMapping("/{id}")
	void deleteUserById(@PathVariable("id") long userId) {
		userService.deleteUserById(userId);
	}

	@PutMapping("/{id}/roles")
	UserDto setRoles(@PathVariable("id") long id, @RequestBody RoleInputDto roles) {
		AppUser user = userService.setRolesForUser(id, roles.roles);
		return new UserDto(user);
	}
}
