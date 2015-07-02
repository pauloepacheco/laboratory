package br.com.ulbra.tcc.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.dao.BasicDao;

/**
 * The Basic Service Sample Class
 * 
 * @author Paulo Pacheco
 *
 */
@Service(ServiceBuilder.BASIC_SERVICE)
public class BasicServiceImpl implements BasicService {

	@Autowired
	private transient BasicDao basicDao;
	
	public void displayMessageUsingDependencyInjection() {
		basicDao.showMessage();
	}

}
