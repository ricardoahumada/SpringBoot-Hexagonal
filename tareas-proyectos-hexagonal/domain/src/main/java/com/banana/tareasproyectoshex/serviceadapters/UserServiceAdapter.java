package com.banana.tareasproyectoshex.serviceadapters;

import java.util.Collection;

import com.banana.tareasproyectoshex.models.User;
import com.banana.tareasproyectoshex.ports.UserRepository;
import com.banana.tareasproyectoshex.ports.UserService;
import org.springframework.transaction.annotation.Transactional;


public class UserServiceAdapter implements UserService {

	private UserRepository userRepo;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepo = userRepository;
	}

	@Override
	@Transactional
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
