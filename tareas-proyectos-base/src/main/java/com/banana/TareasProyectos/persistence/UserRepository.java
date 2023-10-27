package com.banana.TareasProyectos.persistence;

import java.util.Collection;

import com.banana.TareasProyectos.models.User;

public interface UserRepository {
	public User findOne(Long id);
	
	public Collection<User> findAll();
	
	public User save(User user);

	public void delete(User user);
}
