package br.com.ulbra.tcc.common.databasetask;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ulbra.tcc.common.AbstractJUnitCommonTest;
import br.com.ulbra.tcc.common.dao.dataquality.DataQualityValidatorDao;

/**
 * The DataQualityValidatorDaoImplTest Class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityValidatorDaoImplTest extends AbstractJUnitCommonTest{

	@Autowired
	private transient DataQualityValidatorDao dataQualityValidatorDao;
	
	@Test
	public void testGetPrimaryKeyColumnNameFromTable(){
		String pkColumnName = dataQualityValidatorDao.getPrimaryKeyColumnNameFromTable("tcc_schema", "address");
		if(pkColumnName != null){
			System.out.println("PK Column[" + pkColumnName + "].");	
		} else {
			Assert.fail();
		}
	}
}
