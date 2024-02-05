package JPAControladorDao;

import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import entidades.Departamento;
import entidades.Empleado;
import entidades.Proyecto;






//@Stateless
public class ProyectoFacadeImpl extends AbstractFacadeJPAImpl<Proyecto> implements ProyectoFacade {


public ProyectoFacadeImpl() {

	super(Proyecto.class);

}

public List<Proyecto> mostrarTodos() {
    
    TypedQuery<Proyecto> q = this.getEm().createQuery("SELECT p FROM Proyecto AS p",Proyecto.class);
	return q.getResultList();
}

/* devuelve la lista de los proyectos del departamento de código dep  */
public List<Proyecto> buscarProyectosDeDepto(Integer dep) {
    
    TypedQuery<Proyecto> q = this.getEm().createQuery("SELECT p FROM Proyecto p WHERE p.codDept.codDept=:seleccionado", Proyecto.class);
	q.setParameter("seleccionado", dep);
	
    return q.getResultList();
}

/* devuelve la lista de los proyectos del departamento de nombre nombreDep*/
//con consultas anidadas
public List<Proyecto> buscarProyectosDeDepto(String nombreDep) {
    
    TypedQuery<Proyecto> q = this.getEm().createQuery("SELECT p FROM Proyecto p WHERE p.codDept IN "
    		+ "                   (SELECT d.codDept FROM Departamento d WHERE d.dnombre=:seleccionado)", Proyecto.class);
	q.setParameter("seleccionado", nombreDep);
	
    return q.getResultList();
}


//otra forma:con JOIN
public List<Proyecto> buscar2ProyectosDeDepto(String nombreDep) {
	
	/*CONSULTA SQL
	 * -----------------------------------------------
	 * SELECT p
	 * FROM proyecto p JOIN departamento d ON p.codDept=d.codDept
	 * WHERE  d.codDept='1';
	 * 
	 * 
	*/
	
    TypedQuery<Proyecto> q = this.getEm().createQuery("SELECT p FROM Proyecto p JOIN Departamento d "
    		                                   + "ON p.codDept=d.codDept WHERE  d.dnombre=:seleccionado", Proyecto.class);
	q.setParameter("seleccionado", nombreDep);
	
    return q.getResultList();
}

public void actualizaNombre(String nombre, Integer proy) {
//	 Query q = this.getEm().createQuery("UPDATE Proyecto  SET pnombre =:nom WHERE codProy=:pro");
//	 q.setParameter("nom", nombre);
//	 q.setParameter("pro", proy);
	 Query q = this.getEm().createQuery("UPDATE Proyecto p SET p.pnombre='hola' WHERE p.codProy=1");
	 q.executeUpdate();
	
	
}

}


