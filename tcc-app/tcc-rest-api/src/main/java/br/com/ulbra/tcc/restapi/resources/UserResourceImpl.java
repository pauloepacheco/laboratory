package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.ulbra.tcc.common.entity.User;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.user.UserService;

/**
 * The UserResourceImpl Class
 * 
 * @author Paulo Pacheco
 *
 */

@Component
@Path(URIResourceBuilder.UserResource.USER_URI)
public class UserResourceImpl implements URIResourceBuilder {
	
	private static final Logger LOGGER = Logger.getLogger(UserResourceImpl.class);
	
	@GET
	@Path(URIResourceBuilder.UserResource.GET_ALL_USERS_URI)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllRegisteredUsers(){
				
		UserService userService = ServiceLocator.
				getServiceInstance(ServiceBuilder.USER_SERVICE, UserService.class);
		
		LOGGER.info("Request to get the list of registed users initiated.");
		
		return userService.getListRegisteredUsers();
	}
}
