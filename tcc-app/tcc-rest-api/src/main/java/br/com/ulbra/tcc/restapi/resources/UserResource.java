package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.ulbra.tcc.common.entity.User;
import br.com.ulbra.tcc.restapi.constants.URIBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.user.UserService;

@Path(URIBuilder.USER_URI)
public class UserResource {
	
	/*@Autowired
	private UserService userService;*/
	
	@GET
	@Path(URIBuilder.GET_ALL_USERS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllRegisteredUsers(){
				
		UserService userService = ServiceLocator.
				getServiceInstance(ServiceBuilder.USER_SERVICE, UserService.class);
		
		return userService.getListRegisteredUsers();
	}
}
