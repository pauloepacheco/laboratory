package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.ulbra.tcc.common.entity.User;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder.UserResource;
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
@Path(UserResource.USER_URI)
public class UserResourceImpl implements UserResource {
	
	private static final Logger LOGGER = Logger.getLogger(UserResourceImpl.class);
	
	@GET
	@Path(UserResource.GET_ALL_USERS_URI)
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllRegisteredUsers() throws TCCWebServiceException{
			
		UserService userService = null;
		try{
			userService = ServiceLocator.getServiceInstance(ServiceBuilder.USER_SERVICE, UserService.class);
			LOGGER.info("Request to get the list of registed users initiated.");
		} catch(TCCTechnicalException tte){
			throw new TCCWebServiceException(tte.getMessage(), tte);
		}
		return userService.getListRegisteredUsers();
	}
}
