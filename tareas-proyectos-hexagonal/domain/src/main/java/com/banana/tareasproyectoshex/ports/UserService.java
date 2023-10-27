package com.banana.tareasproyectoshex.ports;

import java.util.Collection;

import com.banana.tareasproyectoshex.models.User;

public interface UserService {
	public User findById(Long id);

	public Collection<User> findAll();

	public User add(User user);

	public void delete(Long id);

}
