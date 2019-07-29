package es.bit.TareasProyectos.persistence;

import java.util.Collection;

import es.bit.TareasProyectos.models.User;

public interface UserRepository {
	public User findOne(Long id);
	
	public Collection<User> findAll();
	
	public User save(User user);

	public void delete(User user);
}
