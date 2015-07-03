package br.com.ulbra.tcc.common.user;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ulbra.tcc.common.dao.user.UserDao;
import br.com.ulbra.tcc.common.entity.User;
import br.com.ulbra.tcc.common.AbstractJUnitCommonTest;

/**
 * The User Dao Test Implementation Class
 * 
 * @author Paulo Pacheco
 *
 */
public class UserDaoTestImpl extends AbstractJUnitCommonTest {

	@Autowired
	private transient UserDao userDao;
	
	@Test
	public void testFindUserById(){
		User user = userDao.findUserById(new BigDecimal("2"));
		
		Assert.assertTrue(user != null);
	}
	
	@Test
	public void testGetAllRegisteredUsers(){
		List<User> users = userDao.getAllRegisteredUsers();
		
		Assert.assertTrue(users != null && !users.isEmpty());
	}

}
