package br.com.ulbra.tcc.services.service.dataquality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulbra.tcc.common.constants.CommonConstants;
import br.com.ulbra.tcc.common.dao.dataquality.DataQualityValidatorDao;
import br.com.ulbra.tcc.common.exception.TCCBusinessException;
import br.com.ulbra.tcc.common.exception.TCCTechnicalException;
import br.com.ulbra.tcc.common.vo.databasetask.ColumnVO;
import br.com.ulbra.tcc.common.vo.databasetask.TableVO;
import br.com.ulbra.tcc.common.vo.dataquality.ColumnRegexVO;
import br.com.ulbra.tcc.common.vo.dataquality.DataQualityValidatorVO;
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
	private transient DataQualityObjectMapper dataQualityObjectMapper;
	
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<DataQualityValidatorVO> performCustomValidations(List<TableVO> tableVOs) 
			throws TCCTechnicalException, TCCBusinessException{

		final String sqlPattern = "SELECT [columns] FROM [schema].[table];";
		List<Object> queryRows = new ArrayList<Object>();
		List<DataQualityValidatorVO> dataQualityValidatorVOs = new ArrayList<DataQualityValidatorVO>();
		ColumnRegexVO columnRegexVO = null;		
		
		for (TableVO tableVO : tableVOs) {
			if(tableVO != null){
				
				columnRegexVO = buildDinamicSQLColumns(tableVO);
				
				if(columnRegexVO == null){
					continue;
				}
				
				final String sqlStatement = sqlPattern.
						replace("[columns]", columnRegexVO.getSqlColumns()).
						replace("[schema]", tableVO.getSchema()).
						replace("[table]", tableVO.getTableName());
				
				LOGGER.info("SQL query to be performed[" + sqlStatement + "].");
				
				try{
					queryRows = dataQualityValidatorDao.processDataQualityRequest(sqlStatement);
					
					if(queryRows != null && !queryRows.isEmpty()){
						
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put("rows", queryRows);
						parameters.put("table", tableVO.getTableName());
						parameters.put("schema", tableVO.getSchema());
						parameters.put("columns", columnRegexVO.getSqlColumns());
						parameters.put("regexByColumn", columnRegexVO.getRegex());						
						
						DataQualityValidatorVO response = dataQualityObjectMapper.buildDataQualityValidatorVO(parameters);
						
						if(response != null){
							dataQualityValidatorVOs.add(response);	
						}
						
					} else {
						LOGGER.info("No results found for above SQL statement");
					}
				} catch(DataAccessException dae){
					LOGGER.error("DataAccessException ocurred when executing data quality validator.", dae);
					throw new TCCTechnicalException("An error occurred when trying to access information from database.", dae);
				} catch (Exception exc) {
					LOGGER.error("Exception ocurred when executing data quality validator.", exc);
					throw new TCCTechnicalException("Unexpected error occurred when executing custom validations.", exc);
				}
			}
		}			
		return dataQualityValidatorVOs;
	}
	
	private ColumnRegexVO buildDinamicSQLColumns(final TableVO tableVO){
				
		StringBuilder sqlColumnBuilder = new StringBuilder();
		List<String> tempRegexList = new ArrayList<String>();
		ColumnRegexVO columnRegexVO = null;
		
		for (ColumnVO columnVO : tableVO.getColumnVOs()) {
			
			if(columnVO.getRegex() != null && !columnVO.getRegex().isEmpty()){
				sqlColumnBuilder.append(columnVO.getColumnName());
				sqlColumnBuilder.append(CommonConstants.COMMA);
				tempRegexList.add(columnVO.getRegex());
			}
		}
		
		if(sqlColumnBuilder.length() > 0){
			sqlColumnBuilder.deleteCharAt(sqlColumnBuilder.length() - 1);
			
			columnRegexVO = new ColumnRegexVO();
			columnRegexVO.setRegex(tempRegexList);
			columnRegexVO.setSqlColumns(sqlColumnBuilder.toString());
			return columnRegexVO;
		}
		return columnRegexVO;
	}
}
