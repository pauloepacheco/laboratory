package br.com.ulbra.tcc.services.service.user;

import java.math.BigDecimal;
import java.util.List;

import br.com.ulbra.tcc.common.entity.User;

/**
 * The User Service class
 * 
 * @author Paulo Pacheco
 *
 */
public interface UserService {

	/**
	 * Finds the registered user by id
	 * 
	 * @param userId the user id
	 * @return the user
	 */
	public User findRegisteredUserById(BigDecimal userId);
	
	/**
	 * Gets the list of all registered users
	 * 
	 * @return the list of users
	 */
	public List<User> getListRegisteredUsers();
}
