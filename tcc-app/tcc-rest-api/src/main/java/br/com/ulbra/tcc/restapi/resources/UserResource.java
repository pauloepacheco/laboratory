package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import br.com.ulbra.tcc.common.entity.User;
import br.com.ulbra.tcc.common.exception.TCCWebServiceException;

/**
 * The UserResourceImpl Interface
 * 
 * @author Paulo Pacheco
 *
 */

public interface UserResource {

	public List<User> getAllRegisteredUsers() throws TCCWebServiceException;
}
