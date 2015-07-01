package br.com.ulbra.tcc.services.dao;

import org.springframework.stereotype.Repository;

/**
 * The Basic Dao Sample Class
 * 
 * @author Paulo Pacheco
 *
 */

@Repository
public class BasicDaoImpl implements BasicDao {

	public void showMessage() {
		System.out.println("This message is being displayed " + 
				"from " + this.getClass());
	}	
}
