package es.bit.tareasproyectoshex.serviceadapters;

import java.util.Collection;

import es.bit.tareasproyectoshex.models.User;
import es.bit.tareasproyectoshex.ports.UserRepository;
import es.bit.tareasproyectoshex.ports.UserService;
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
