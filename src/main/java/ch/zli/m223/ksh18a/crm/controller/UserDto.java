package ch.zli.m223.ksh18a.crm.controller;

import java.util.List;

import ch.zli.m223.ksh18a.crm.model.AppUser;

public class UserDto {

	public Long id;
	public final String userName;
	public List<String> roles;

	public UserDto(AppUser user) {
		this.id = user.getId();
		this.userName = user.getUsername();
		this.roles = user.getRoles();
	}

}
