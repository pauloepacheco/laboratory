package br.com.ulbra.tcc.common.table;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import br.com.ulbra.tcc.common.dao.table.TableDao;
import br.com.ulbra.tcc.common.vo.column.ColumnVO;
import br.com.ulbra.tcc.common.vo.table.TableVO;
import br.com.ulbra.tcc.common.AbstractJUnitCommonTest;

/**
 * The Table Dao Test Implementation Class
 * 
 * @author Paulo Pacheco
 *
 */
public class TableDaoTestImpl extends AbstractJUnitCommonTest {

	@Autowired
	private transient TableDao tableDao;
	
	@Test
	public void testGetTablesFromDB(){
		
		try{
			List<TableVO> tableVOs = tableDao.getTablesFromDB();
			
			if(tableVOs != null && !tableVOs.isEmpty()){
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
			List<ColumnVO> ColumnVOs = tableDao.getColumnsFromTable("users");
			
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
