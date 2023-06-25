package br.org.mnf.dao;

import br.org.mnf.model.User;

public interface UserDao {
	
	public User customFindMethod(Long id);

}
