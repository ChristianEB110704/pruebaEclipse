package JPAControladorDao;

import java.util.List;

import entidades.Departamento;
import entidades.PlazaParking;

public interface PlazaParkingFacade extends AbstractFacadeJPA<PlazaParking>{
	 public List<PlazaParking> mostrarTodos();
	
}