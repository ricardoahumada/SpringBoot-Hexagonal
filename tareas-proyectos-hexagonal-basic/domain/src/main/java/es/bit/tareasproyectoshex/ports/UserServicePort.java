package es.bit.tareasproyectoshex.ports;

import java.util.Collection;

import es.bit.tareasproyectoshex.models.User;

public interface UserServicePort {
	public User findById(Long id);

	public Collection<User> findAll();

	public User add(User user);

	public void delete(Long id);

}
