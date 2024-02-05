package JPAControladorDao;

import java.util.List;


import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import entidades.Departamento;
import entidades.Empleado;




//@Stateless
public class DepartamentoFacadeImpl extends AbstractFacadeJPAImpl<Departamento> implements DepartamentoFacade {


public DepartamentoFacadeImpl() {

	super(Departamento.class);

}

public List<Departamento> buscarTodos() {
    
    TypedQuery<Departamento> q = this.getEm().createQuery("SELECT p FROM Departamento AS p",Departamento.class);
	return q.getResultList();
}


public List<Empleado> buscarEmpleadosDeDpto(Integer dep) {
    
    List<Empleado> q = this.getEm().find(Departamento.class, dep).getEmpleados();
	return q;
}


}


