import java.math.BigDecimal;
import java.util.List;

import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.model.User;
import br.com.ulbra.tcc.services.sample.BasicService;
import br.com.ulbra.tcc.services.user.UserService;


public class Main {

	public static void main(String[] args) {
		
		BasicService basicService = ServiceLocator.getServiceInstance(
				ServiceBuilder.BASIC_SERVICE, BasicService.class);
		
		basicService.displayMessageUsingDependencyInjection();
		
		UserService userService = ServiceLocator.getServiceInstance(
				ServiceBuilder.USER_SERVICE, UserService.class);
		
		List<User> users = userService.getListRegisteredUsers();
		
		if(users != null && users.size() > 0){
			System.out.println("User from DB ".concat(users.get(0).getName()));
		}
		
		User user = userService.findRegisteredUserById(
				new BigDecimal("45"));
		if(user != null){
			System.out.println("user name " + user.getName());
		}
	}

}
