package br.com.ulbra.tcc.common.databasetask;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.common.AbstractJUnitCommonTest;
import br.com.ulbra.tcc.common.dao.databasetask.DatabaseTaskDao;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;

/**
 * The DatabaseTaskDaoImplTest Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DatabaseTaskDaoImplTest extends AbstractJUnitCommonTest{

	@Autowired
	private transient DatabaseTaskDao databaseTaskDao;
	
	@Test
	public void testGetTablesFromDB(){
		
		try{
			List<SchemaVO> schemaVOs = databaseTaskDao.getSchemasFromDB();
			
			if(schemaVOs != null && !schemaVOs.isEmpty()){
				Assert.assertTrue(true);
			} else{
				Assert.assertTrue(false);
			}
		} catch(DataAccessException dae){
			Assert.assertTrue(false);
		}		
	}
	
	@Test
	public void testGetColumnsFromTable(){
		try{
			TableVO tableVO = databaseTaskDao.getColumnsFromTable("public", "phone");
			
			if(tableVO != null){
				Assert.assertTrue(true);
			} else{
				Assert.assertTrue(false);
			}
		} catch(DataAccessException dae){
			Assert.assertTrue(false);
		}
	}
}
