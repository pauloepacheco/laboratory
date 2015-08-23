package br.com.ulbra.tcc.services.databasetask;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Test;

import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.services.common.AbstractJUnitServiceTest;
import br.com.ulbra.tcc.services.service.databasetask.DatabaseTaskService;


/**
 * The DatabaseTaskServiceImplTest Class
 * 
 * @author Paulo Pacheco
 *
 */

public class DatabaseTaskServiceImplTest extends AbstractJUnitServiceTest{

	@Autowired
	private transient DatabaseTaskService databaseTaskService;
	
	@Test
	public void testGetTablesFromDB(){
		
		try{
			List<SchemaVO> schemaVOs = databaseTaskService.getInitialLoad();
			if(schemaVOs != null && !schemaVOs.isEmpty()){
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch(Exception exc){
			Assert.assertTrue(exc.getMessage(), false);
		}
	}
}
