package br.com.ulbra.tcc.services.service.dataquality;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulbra.tcc.common.dao.common.QueryTransformer;
import br.com.ulbra.tcc.common.dao.constants.CommonConstants;
import br.com.ulbra.tcc.common.dao.databasetask.DataQualityValidatorDao;
import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.ws.response.DataQualityValidatorResponse;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.databasetask.DatabaseTaskServiceImpl;

/**
 * The DataQualityValidatorServiceImpl Class
 * 
 * @author Paulo Pacheco
 *
 */
@Service(ServiceBuilder.DATA_QUALITY_VALIDATOR)
public class DataQualityValidatorServiceImpl implements DataQualityValidatorService {
	
	private static final Logger LOGGER = Logger.getLogger(DatabaseTaskServiceImpl.class);
	
	@Autowired
	private transient DataQualityValidatorDao dataQualityValidatorDao;
	
	@Autowired
	private transient QueryTransformer queryTransformer;

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public DataQualityValidatorResponse processDataQualityRequest(List<TableVO> tableVOList) {

		final String sqlPattern = "SELECT [columns] FROM [schema].[table];";
		String sql = null;
		StringBuilder sqlColumnBuilder = new StringBuilder();
		List<Object> daoResponse = new ArrayList<Object>();
		String columns = null;
		
		for (TableVO tableVO : tableVOList) {
			if(tableVO != null){				
				sqlColumnBuilder.setLength(0);
				for (ColumnVO columnVO : tableVO.getColumnVOs()) {
					if(columnVO.getRegex() != null && !columnVO.getRegex().isEmpty()){
						sqlColumnBuilder.append(columnVO.getColumnName());
						sqlColumnBuilder.append(CommonConstants.COMMA);
					}
				}
				sqlColumnBuilder.deleteCharAt(sqlColumnBuilder.length() - 1);
				columns = sqlColumnBuilder.toString();
				
				sql = sqlPattern.replace("[columns]", columns).replace("[schema]", 
						tableVO.getSchema()).replace("[table]", tableVO.getTableName());
				
				LOGGER.info("SQL query to be performed[" + sql + "].");
				try{
					daoResponse = dataQualityValidatorDao.processDataQualityRequest(sql);
					
					if(daoResponse != null && !daoResponse.isEmpty()){
						queryTransformer.buildDataQualityValidatorResponse(daoResponse, tableVO.getSchema(), 
								tableVO.getTableName(), columns);
					}
				} catch(DataAccessException dae){
					LOGGER.error("DataAccessException ocurred when executing data quality validator.", dae);
				} catch (Exception exc) {
					LOGGER.error("Exception ocurred when executing data quality validator.", exc);
				}
			}
		}			
		return null;
	}	
}
