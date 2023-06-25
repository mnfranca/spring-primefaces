package br.org.mnf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.mnf.dao.UserDao;
import br.org.mnf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDao {

}
