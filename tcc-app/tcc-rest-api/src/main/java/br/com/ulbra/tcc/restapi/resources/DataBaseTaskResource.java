package br.com.ulbra.tcc.restapi.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.ulbra.tcc.common.vo.table.TableVO;
import br.com.ulbra.tcc.restapi.constants.URIResourceBuilder;
import br.com.ulbra.tcc.services.common.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceBuilder;
import br.com.ulbra.tcc.services.service.table.TableService;

@Path(URIResourceBuilder.DataBaseResource.DATA_BASE_URI)
public class DataBaseTaskResource {

	@GET
	@Path(URIResourceBuilder.DataBaseResource.GET_DB_INFO_URI)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TableVO> getTableDetails(){
		
		
		TableService tableService = ServiceLocator.
				getServiceInstance(ServiceBuilder.TABLE_SERVICE, TableService.class);
						
		return tableService.getTablesAndColumnsFromDB();
	}
}
