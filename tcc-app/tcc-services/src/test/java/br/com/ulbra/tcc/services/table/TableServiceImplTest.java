package br.com.ulbra.tcc.services.table;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Test;

import br.com.ulbra.tcc.common.vo.table.TableVO;
import br.com.ulbra.tcc.services.common.AbstractJUnitServiceTest;
import br.com.ulbra.tcc.services.service.table.TableService;


/**
 * The Table Service Implementation Test Class
 * 
 * @author Paulo Pacheco
 *
 */

public class TableServiceImplTest extends AbstractJUnitServiceTest{

	@Autowired
	private transient TableService tableService;
	
	@Test
	public void testGetTablesFromDB(){
		
		try{
			List<TableVO> tableVOs = tableService.getTablesAndColumnsFromDB();
			if(tableVOs != null && !tableVOs.isEmpty()){
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		} catch(Exception exc){
			Assert.assertTrue(exc.getMessage(), false);
		}
	}
}
