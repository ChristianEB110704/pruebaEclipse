package JPAControladorDao;

import java.util.List;


import entidades.Direccion;


public interface DireccionFacade extends AbstractFacadeJPA<Direccion>{
	 public List<Direccion> mostrarTodos();
	
}