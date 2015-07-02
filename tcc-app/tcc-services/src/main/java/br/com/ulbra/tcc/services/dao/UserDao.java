package br.com.ulbra.tcc.services.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.services.model.User;

public interface UserDao{

	/**
	 * Finds the user by a specific id
	 * 
	 * @param userId the user id
	 * @return the user entity
	 * @throws DataAccessException the data access exception
	 */
	public User findUserById(BigDecimal userId) throws DataAccessException;
	
	/**
	 * Gets the list of all registered users
	 * 
	 * @return the list of registered users
	 * @throws DataAccessException the data access exception
	 */
	public List<User> getAllRegisteredUsers() throws DataAccessException;
}
