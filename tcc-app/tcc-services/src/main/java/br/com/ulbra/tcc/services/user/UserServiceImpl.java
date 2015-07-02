package br.com.ulbra.tcc.services.user;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.dao.UserDao;
import br.com.ulbra.tcc.services.model.User;

/**
 * The User Service Implementation Class
 * 
 * @author Paulo Pacheco
 *
 */

@Service(ServiceBuilder.USER_SERVICE)
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private transient UserDao userDao;
	
	public User findRegisteredUserById(BigDecimal userId) {
		
		User user = null;
		try{
			user = userDao.findUserById(userId);
		} catch (DataAccessException dae){
			LOGGER.error("DataAccessException ocorred when trying to get user by id " +
					dae.getLocalizedMessage(), dae);
		}
		return user;
	}
	
	public List<User> getListRegisteredUsers() {
		
		List<User> users = null;
		try{			
			users = userDao.getAllRegisteredUsers();
		} catch (DataAccessException dae){
			LOGGER.error("DataAccessException ocorred when trying to get the list " +
					"of registered users " + dae.getLocalizedMessage(), dae);
		}
		return users;
	}
}


