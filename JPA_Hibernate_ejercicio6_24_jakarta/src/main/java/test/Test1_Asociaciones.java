package test;
import java.util.List;

import JPAControladorDao.DepartamentoFacadeImpl;
import JPAControladorDao.DireccionFacadeImpl;
import JPAControladorDao.EmpleadoFacadeImpl;
import JPAControladorDao.PlazaParkingFacadeImpl;
import JPAControladorDao.ProyectoFacadeImpl;
import entidades.Departamento;
import entidades.Direccion;
import entidades.Empleado;
import entidades.PlazaParking;
import entidades.Proyecto;

public class Test1_Asociaciones {
	
	/**
	 * Prueba para crear asociaciones. 
	 * ES COPIA DE EJERCICIO 3   Test3_Asociaciones_OnetoManyUnidireccional
	 * 
	 *  Creamos una BD nueva ejercicio6. Ejecutamos una vez para crear la BD y luego en persistence.xml 
	 *  ponemos la opción a validate.
	 */
	
	public static void main(String[] args) {
		Direccion d1= new Direccion (1l,"calle1","ciudad1","pais1",41001l);
		Direccion d2= new Direccion (2l,"calle2","ciudad2","pais2",41201l);
		
		PlazaParking p1=new PlazaParking("p100",1, 00);
		PlazaParking p2=new PlazaParking("p101",1, 01);
		
		Empleado e1 = new Empleado("e1","Luisa", "Jimenez", d1);
		Empleado e2 = new Empleado("e2","Luis", "RUS", d2);
		
		Departamento dep1= new Departamento(1,"contabilidad",10000.);
		
		Proyecto py1=new Proyecto(1,"proy1");
		Proyecto py2=new Proyecto(2,"proy2");
		
		
		//el orden  es importante
		PlazaParkingFacadeImpl pf= new PlazaParkingFacadeImpl();
		DireccionFacadeImpl df= new DireccionFacadeImpl();
		EmpleadoFacadeImpl ef= new EmpleadoFacadeImpl();
		
		DepartamentoFacadeImpl dpf=new DepartamentoFacadeImpl();
		ProyectoFacadeImpl pyf=new ProyectoFacadeImpl();
		//creo las direcciones
		df.create(d1);
		df.create(d2);
		//creo las plazas
		pf.create(p1);
		pf.create(p2);
		
		//creo un empleado
		e1.setPlaza(p1);
		p1.setEmpleado(e1);
		ef.create(e1);
		
		
		//creo un empleado
		e2.setPlaza(p2);
		p2.setEmpleado(e2);
		ef.create(e2);
		
		
		//crea un depto y le asigna 2 empleados
		dpf.create(dep1);
		dep1.addEmpleado(e1);
		dep1.addEmpleado(e2);
		//dpf.update(dep1);   //no hace falta actualizar 
		
	
		dep1.addProyecto(py1);
		dep1.addProyecto(py2);
		//dpf.update(dep1); 
		
		pyf.create(py1);
		pyf.create(py2);
		
	
		System.out.println( "Consultas:");
		List<Empleado> listaEmpleados=ef.mostrarTodos();
		System.out.println( listaEmpleados);
		
		
		
		System.out.println( "**                                                 **");
		System.out.println( "**   CONSULTAS: MOSTRAR LOS EMPLEADOS DEL DEPTO 1  **");
		System.out.println (dpf.buscarTodos().get(0).getEmpleados());
		
		System.out.println( "**                                                 **");
		System.out.println( "**   CONSULTAS: MOSTRAR LOS PROYECTOS DEL DEPTO 1  **");
		System.out.println (dpf.buscarTodos().get(0).getProyectos());
		
		ef.getEm().close();
		
	
	}
	
	
}
