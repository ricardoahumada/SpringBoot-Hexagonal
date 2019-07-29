package es.bit.TareasProyectos.services;

import java.util.Collection;

import es.bit.TareasProyectos.models.User;
import es.bit.TareasProyectos.persistence.UserRepository;

public class UserServiceImplementation implements UserService {

	private UserRepository userRepo;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepo = userRepository;
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
