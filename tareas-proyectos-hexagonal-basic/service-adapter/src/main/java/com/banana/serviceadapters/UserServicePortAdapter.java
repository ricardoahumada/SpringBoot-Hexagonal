package com.banana.serviceadapters;

import java.util.Collection;

import com.banana.tareasproyectoshex.models.User;
import com.banana.tareasproyectoshex.ports.UserRepositoryPort;
import com.banana.tareasproyectoshex.ports.UserServicePort;


public class UserServicePortAdapter implements UserServicePort {

	private UserRepositoryPort userRepo;

	public void setUserRepository(UserRepositoryPort userRepositoryPort) {
		this.userRepo = userRepositoryPort;
	}

	@Override
	public User findById(Long id) {
		return userRepo.findOne(id);
	}

	@Override
	public Collection<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User add(User user) {
		return userRepo.save(user);
	}

	@Override
	public void delete(Long id) {
		User user=userRepo.findOne(id);
		if(user!=null) userRepo.delete(user);

	}
}
