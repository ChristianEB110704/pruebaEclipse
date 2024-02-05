package JPAControladorDao;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.TypedQuery;

import entidades.Departamento;
import entidades.Empleado;






//@Stateless
public class EmpleadoFacadeImpl extends AbstractFacadeJPAImpl<Empleado> implements EmpleadoFacade {


public EmpleadoFacadeImpl() {

	super(Empleado.class);

}

public List<Empleado> mostrarTodos() {
    
    TypedQuery<Empleado> q = this.getEm().createQuery("SELECT p FROM Empleado AS p",Empleado.class);
	return q.getResultList();
}















/**
 * TypedQuery<Jugador> q = em.createQuery(
				"SELECT "
				+ "		j "
				+ "FROM "
				+ "		Jugador j "
				+ "WHERE "
				+ "		j.codjugador IN (SELECT "
				+ "							pj.id.codjugador "
				+ "						FROM "
				+ "							Pj pj "
				+ "						WHERE "
				+ "							pj.id.codpartido IN (SELECT "
				+ "													p.codPartido "
				+ "												FROM "
				+ "													Partido p "
				+ "												WHERE "
				+ "													p.temporada=:seleccionada))", Jugador.class);
		q.setParameter("seleccionada", temporada);
		return q.getResultList();
 * @param dep
 * @return
 */


/** 
 *  public List<Pj> findAllTotal(Temporada temporada) {
		TypedQuery<Object[]> q=em.createQuery("SELECT pj, sum(pj.canastas),sum(pj.rebotes),sum(pj.asistencias) FROM Pj pj join pj.partido p  where p.temporada.codtemp=:temp GROUP BY pj.jugador",Object[].class);
		
		CONSULTA SQL
		 * -----------------------------------------------
		 * SELECT sum(pj.CANASTAS),sum(pj.ASISTENCIAS),sum(pj.REBOTES),j.NOMBREJUGADOR 
		 * FROM pj pj JOIN jugador j ON pj.CODJUGADOR=j.CODJUGADOR
		 * WHERE CODPARTIDO IN (SELECT p.CODPARTIDO FROM partido p WHERE p.codtempo='t1')
		 * GROUP BY j.NOMBREJUGADOR;
		 * 
		 * 		
		
		q.setParameter("temp", temporada.getCodtemp());
		List<Pj>res=new ArrayList<>();
		Object[] results = q.getResultList().toArray();
		
		//Convertimos el resultado a objetos Pj
		for (Object aux : results) {
			Pj pj=(Pj)((Object[])aux)[0];
			pj.setCanastas((int)(long)((Object[])aux)[1]);
			pj.setRebotes((int)(long)((Object[])aux)[2]);
			pj.setAsistencias((int)(long)((Object[])aux)[3]);
			res.add(pj);
		}
		return res;
	}
 *  
 * @param dep
 * @return
 */
//en una One to Many unidireccional no puedo buscar un empleado  en  Departamento. No tiene sentido. Directamente en Departamento accedo a la lista
/*INCORRECTO
 * public List<Empleado> buscarEmpleadosDeDepto(Integer dep) {
    
    TypedQuery<Empleado> q = this.getEm().createQuery("SELECT e FROM Empleado e WHERE e.codEmpleado IN (SELECT d.empleados. FROM Departamento d WHERE d.codDept=:seleccionado)", Empleado.class);
	q.setParameter("seleccionado", dep);
	
    return q.getResultList();
}
*/

}


