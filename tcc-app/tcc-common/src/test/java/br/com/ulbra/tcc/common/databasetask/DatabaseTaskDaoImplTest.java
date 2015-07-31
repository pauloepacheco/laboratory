package br.com.ulbra.tcc.common.databasetask;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.common.dao.constants.DaoConstants;
import br.com.ulbra.tcc.common.dao.databasetask.DatabaseTaskDao;
import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.SchemaVO;
import br.com.ulbra.tcc.common.AbstractJUnitCommonTest;

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
			List<ColumnVO> ColumnVOs = databaseTaskDao.getColumnsFromTable("public", "phone");
			
			if(ColumnVOs != null && !ColumnVOs.isEmpty()){
				Assert.assertTrue(true);
			} else{
				Assert.assertTrue(false);
			}
		} catch(DataAccessException dae){
			Assert.assertTrue(false);
		}
	}
}
