package JPAControladorDao;

import java.util.List;

import entidades.Departamento;
import entidades.Proyecto;

public interface ProyectoFacade extends AbstractFacadeJPA<Proyecto>{
	 public List<Proyecto> mostrarTodos();
	 public List<Proyecto> buscarProyectosDeDepto(Integer dep);
	 public List<Proyecto> buscar2ProyectosDeDepto(String  nombreDep);
	 public void actualizaNombre(String nombre, Integer proy);
	
}