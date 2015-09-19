package br.com.ulbra.tcc.common.dao.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import br.com.ulbra.tcc.common.dao.common.AbstractDao;
import br.com.ulbra.tcc.common.entity.User;

/**
 * The User Dao Implementation Class
 * 
 * @author Paulo Pacheco
 *
 */
@Repository
public class UserDaoImpl extends AbstractDao<User, BigDecimal> implements UserDao {

	public User findUserById(BigDecimal userId) throws DataAccessException {
		return findById(userId);
	}
	
	public List<User> getAllRegisteredUsers() throws DataAccessException {		
		return findAll();		
	}
	
}
