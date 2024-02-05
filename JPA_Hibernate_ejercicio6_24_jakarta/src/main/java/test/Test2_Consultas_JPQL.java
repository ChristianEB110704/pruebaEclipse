package test;
import java.util.List;


import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import JPAControladorDao.DepartamentoFacadeImpl;
import JPAControladorDao.DireccionFacadeImpl;
import JPAControladorDao.EmpleadoFacadeImpl;
import JPAControladorDao.PlazaParkingFacadeImpl;
import JPAControladorDao.ProyectoFacadeImpl;
import entidades.Departamento;
import entidades.Direccion;
import entidades.Empleado;

import entidades.Proyecto;

public class Test2_Consultas_JPQL {
	
	/**
	 * Prueba para crear asociaciones. 
	 * ES COPIA DE EJERCICIO 3   Test3_Asociaciones_OnetoManyUnidireccional
	 * 
	 *  Creamos una BD nueva ejercicio6. Ejecutamos una vez para crear la BD y luego en persistence.xml p
	 *  ponemos la opción a validate.
	 */
	
	public static void main(String[] args) {
		
		//el orden importa
		PlazaParkingFacadeImpl pf= new PlazaParkingFacadeImpl();
		DireccionFacadeImpl df= new DireccionFacadeImpl();
		EmpleadoFacadeImpl ef= new EmpleadoFacadeImpl();
		
		DepartamentoFacadeImpl dpf=new DepartamentoFacadeImpl();
		ProyectoFacadeImpl pyf=new ProyectoFacadeImpl();
		/*
		 * Ejercicio 1:  Crea un método con consulta JPQL y uso de createQuery que
		 * devuelva todos los empleados de la empresa
		 */
		//*** Ejecución de sentencias JPQL con createQuery
		//en el método mostrarTodos
		System.out.println( "****                                              ****");
		System.out.println( "** Ejercicio 1  CONSULTAS: MOSTRAR todos LOS EMPLEADOS");
		System.out.println( "****                                              ****");
		List<Empleado> listaEmpleados=ef.mostrarTodos();
		System.out.println( listaEmpleados);
		
			
		/*
		 * Ejercicio 2:  Crea un método  que  devuelva todos Los proyectos del depto de código 1
		 * 
		 * Ejecución de sentencias JPQL (con createQuery) con parámetros dinámicos
		 */
		System.out.println( "****                                                        ****");
		System.out.println( "**  Ejercicio 2 CONSULTA: MOSTRAR todos Los proyectos del depto1");
		System.out.println( "****                                                        ****");
		System.out.println( pyf.buscarProyectosDeDepto(1));
		
		
		System.out.println( "****                                                                                ****");
		System.out.println( "**  Ejercicio 3 CONSULTA: MOSTRAR todos Los proyectos del depto de nombre 'contabilidad'");
		System.out.println( "****                                                                                ****");
		//con notación  punto
		
		//con consultas anidadas
		System.out.println(pyf.buscarProyectosDeDepto("contabilidad"));
				
		//con JOIN
		//System.out.println(pyf.buscar2ProyectosDeDepto("contabilidad"));
						
		System.out.println (dpf.buscarTodos().get(0).getProyectos());  //sin JPQL
		
		
		/* Ejercicio 3:  Crea un método  que  devuelva todos los empleados del depto1 */
		
		
		System.out.println( "**  Ejercicio 3 CONSULTAS: MOSTRAR LOS EMPLEADOS DEL DEPTO 1  **");
		System.out.println (dpf.buscarTodos().get(0).getEmpleados()); //sin JPQL
		System.out.println (dpf.buscarEmpleadosDeDpto(1)); //sin JPQL
	
		
		
		
		/*
		 * Ejercicio 4:  Crea una consulta estática findAll que devuelva todos los empleados
		 *  
		 */
		/*Las consultas con nombre son diferentes de las sentencias dinámicas que hemos visto 
		 * hasta ahora en el sentido de que, una vez definidas, no pueden ser modificadas: son leídas 
		 * y transformadas en sentencias SQL cuando el programa arranca por primera vez, en lugar de 
		 * cada vez que son ejecutadas. Este comportamiento estático las hace más eficientes, y por 
		 * tanto ofrecen un mejor rendimiento. Las consultas estáticas se anotan en la entidad
		 */
		
			Query query = ef.getEm().createNamedQuery(Empleado.BUSCAR_TODOS_EMPLEADOS);
			System.out.println( query.getResultList());
			
	
			/*
			 * Ejercicio 5: En una entidad se pueden especificar varias consultas estáticas.
			 *  Crea varias consultas estáticas en Departamento
			 *  
			 */
			TypedQuery<Departamento> query2 = ef.getEm().createNamedQuery(Departamento.ORDENAR_DESC_NOMBRE, Departamento.class);
			//mejor usar la primera
			//Query query2 = ef.getEm().createNamedQuery(Departamento.ORDENAR_DESC_NOMBRE);
			System.out.println( query2.getResultList());
			Query query3 = ef.getEm().createNamedQuery(Departamento.BUSCAR_CODDEPT);
			query3.setParameter("cod", 1);
			System.out.println( query3.getResultList());
			
			
			
			
			/*
			 * Ejercicio 6:
			 *  Crea una consulta nativa de SQL  que  devuelva todas las direcciones
			 *  
			 */
			
			Query query4 = df.getEm().createNativeQuery("SELECT * FROM DIRECCION", Direccion.class);
			System.out.println( query4.getResultList());
					
			/*
			 * Ejercicio 7: 
			 *  Crea una consulta nativa de SQL  que  devuelva EL NOMBRE Y APELLIDOS DE todos los empleados
			 *    
			 */
			
			Query q = ef.getEm().createNativeQuery("SELECT nombre, apellidos FROM EMPLEADO  WHERE CODDEPTO_FK = :dep");
			q.setParameter("dep", 1);
			
			//List de array de object
			List<Object[]> emples2 = q.getResultList();
			for (Object[] a : emples2) {
			    System.out.println("Empleado " + a[0] + " " + a[1]);
				
			}
			
				
			/*Este no es correcto List de List de object
			List<List<Object>> emples3 = q.getResultList();
			for (List a : emples3) {
			    System.out.println("Empleado " + a.get(0) + " " + a.get(1));
				
			}
			
			*/
			
			
			//convierte en un array el List
			Object[] emples = q.getResultList().toArray();
			
			for (Object a : emples) {
			    System.out.println("Empleado " + ((Object[])a)[0] + " " + ((Object[])a)[1]);
				
			}
			
			/*
			 * Ejercicio 8: 
			 *  Crea una consulta nativa de SQL  que  devuelva 
			 *    
			 */
			
			Query q1 = df.getEm().createNativeQuery("SELECT CODDEPT, DNOMBRE, PRESU, count(E.CODDEPTO_FK ) as numEmpleados "
					+ "FROM DEPARTAMENTO D JOIN EMPLEADO E on D.CODDEPT = E.CODDEPTO_FK  GROUP BY D.CODDEPT");
			Object[] deptos = q1.getResultList().toArray();
			 
			for (Object a : deptos) {
			    System.out.println("Depto "+ ((Object[])a)[0] + " " + ((Object[])a)[1] + " " 
			    + ((Object[])a)[2]+ " " + ((Object[])a)[3]);
			           
			}
			
			/*
			 * Ejercicio 9: 
			 *    
			 */
			 Query q2 = df.getEm().createQuery("Select MAX(e.codEmpleado) from Empleado e");
			    Object result =  q2.getSingleResult();
			    System.out.println("El Mayor código de Empleado es :" + result);
			
			  /*
			  * Ejercicio 10: consulta que devuelva el max y el minimo cod empleado
			  *    
			  */  
			    
			    Query qn = df.getEm().createQuery("Select MAX(e.codEmpleado), MIN(e.codEmpleado) from Empleado e");
			    Object[] result2 = (Object[]) qn.getSingleResult();
			    System.out.println("El Mayor y el menor código de Empleado es :" + result2[0]+" "+result2[1] );
			 
			   
			    
			    
			    
			    
			    System.out.println("ESTE ES SOLO UN PROYECTO DE LA RAMA PRUEBA");
	}
	
	
}
