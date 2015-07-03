package br.com.ulbra.tcc.services.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Test;

import br.com.ulbra.tcc.services.model.User;
import br.com.ulbra.tcc.services.user.UserService;


/**
 * The User Service Implementation Test Class
 * 
 * @author Paulo Pacheco
 *
 */

public class UserServiceImplTest extends AbstractServiceTest{

	@Autowired
	private transient UserService userService;
	
	@Test
	public void testFindRegisteredUserById(){
		
		User user = userService.findRegisteredUserById(
				new BigDecimal("2"));
		
		Assert.assertTrue(user != null);
	}

	@Test
	public void testGetListRegisteredUsers(){
		
		List<User> user = userService.getListRegisteredUsers();
		
		if(user == null || user.size() == 0){
			Assert.fail();
		}
	}
}
