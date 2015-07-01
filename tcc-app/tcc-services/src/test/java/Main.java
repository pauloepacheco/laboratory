import br.com.ulbra.tcc.services.BasicService;
import br.com.ulbra.tcc.services.ServiceLocator;
import br.com.ulbra.tcc.services.constants.ServiceRepository;


public class Main {

	public static void main(String[] args) {
		
		BasicService basicService = ServiceLocator.getServiceInstance(
				ServiceRepository.BASIC_SERVICE, BasicService.class);
		
		basicService.displayMessageUsingDependencyInjection();

	}

}
