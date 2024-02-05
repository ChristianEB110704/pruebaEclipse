package JPAControladorDao;

import java.util.List;

import entidades.Departamento;
import entidades.Empleado;

public interface DepartamentoFacade extends AbstractFacadeJPA<Departamento>{
	 public List<Departamento> buscarTodos();
	 public List<Empleado> buscarEmpleadosDeDpto(Integer dep);
	
}