package es.bit.TareasProyectos.services;

import java.util.Collection;

import es.bit.TareasProyectos.models.User;

public interface UsuariosService {
	public User findById(Long id);

	public Collection<User> findAll();

	public User add(User user);

	public void delete(Long id);

}
