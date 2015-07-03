package br.com.ulbra.tcc.common.dao.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.common.entity.User;

/**
 * The User Dao Interface
 * 
 * @author Paulo Pacheco
 *
 */
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
	
	public void teste();
}
