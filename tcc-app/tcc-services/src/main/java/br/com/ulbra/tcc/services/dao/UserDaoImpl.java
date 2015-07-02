package br.com.ulbra.tcc.services.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ulbra.tcc.services.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<User, BigDecimal> implements UserDao {

	public User findUserById(BigDecimal userId){
		return findById(userId);
	}
	
	public List<User> getAllRegisteredUsers() throws DataAccessException{		
		return findAll();		
	}
}
