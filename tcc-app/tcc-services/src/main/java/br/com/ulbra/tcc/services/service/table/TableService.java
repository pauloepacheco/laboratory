package br.com.ulbra.tcc.services.service.table;

import java.util.List;

import br.com.ulbra.tcc.common.vo.table.TableVO;

public interface TableService {
	
	List<TableVO> getTablesAndColumnsFromDB();

}
