package entidades;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="DEPARTAMENTO", catalog = "ejercicio6", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODDEPT")
})
@NamedQueries({
	@NamedQuery(name=Departamento.BUSCAR_CODDEPT, query="SELECT d FROM Departamento d where d.codDept = :cod" ),
	@NamedQuery(name=Departamento.ORDENAR_DESC_NOMBRE, query="SELECT d FROM Departamento d order by d.dnombre desc")
})
public class Departamento implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String BUSCAR_CODDEPT="Departamento.BUSCAR_CODDEPT";
	public static final String ORDENAR_DESC_NOMBRE="Departamento.ORDENAR_DESC_NOMBRE";
	
	@Id
	@Column(name = "CODDEPT", unique = true, nullable = false)
	private Integer codDept;
	
	@Column(name = "DNOMBRE")
	private String dnombre;
	
	@Column(name = "PRESU")
	private Double presu;
	
	//3.4  Asociación unidireccional ONE to MANY sobre Departamento y Empleado.
	/*LA ASOCIACIÓN SE PUEDE EXPRESAR ASÍ: en un depto hay muchos empleados y la columna de clave ajena en 
	 * la tabla EMPLEADO es CODDEPTO*/
	//	Esto crea dos tablas: EMPLEADO y DEPARTAMENTO y en EMPLEADO está la Clave foránea CODDEPTO
	 @OneToMany(targetEntity=Empleado.class)
	 @JoinColumn(name = "CODDEPTO_FK") 
	 private List<Empleado> empleados;	
	//@JoinColumn(name = "CODDEPARTAMENTO") si se le añade esta anotación no crea una tabla intermedia. Probad a quitarla
	
	 
	 
	//3.5 Asociación bidireccional ONE to MANY sobre Departamento y Proyecto.
		 // un depto participa en muchos proyectos y un proyecto lo realiza solo un depart  
		
		@OneToMany(targetEntity=Proyecto.class ,mappedBy="codDept")
		//@JoinColumn(name = "CODDEPTO_FK")  FALLA no permite especificar nombre de columna
		private List<Proyecto> proyectos;
	
	public Departamento() {
		
	}

	
	/**********************************************************************************/
	//IMPT
	public void addProyecto(Proyecto p) {
		this.proyectos.add(p);
		p.setCodDept(this);
		
		
	}
	
	public void removeProyecto(Proyecto p) {
		this.proyectos.remove(p);
		p.setCodDept(null);
	
	}
	//IMPT
	/**********************************************************************************/
	
	public void addEmpleado(Empleado p) {
		this.empleados.add(p);
		
		
	}
	
	public void removeEmpleado(Empleado p) {
	 this.empleados.remove(p);
	
	}
	
	
	//IMPT
	/**********************************************************************************/
	
	public Integer getCodDept() {
		return codDept;
	}

	public void setCodDept(Integer codDept) {
		this.codDept = codDept;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public Double getPresu() {
		return presu;
	}

	public void setPresu(Double presu) {
		this.presu = presu;
	}

	
	@Override
	public String toString() {
		return "Departamento [codDept=" + codDept + ", dnombre=" + dnombre + ", presu=" + presu + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codDept == null) ? 0 : codDept.hashCode());
		result = prime * result + ((dnombre == null) ? 0 : dnombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (codDept == null) {
			if (other.codDept != null)
				return false;
		} else if (!codDept.equals(other.codDept))
			return false;
		if (dnombre == null) {
			if (other.dnombre != null)
				return false;
		} else if (!dnombre.equals(other.dnombre))
			return false;
		return true;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Departamento (String dnombre, Double presu) {
		
		this.dnombre = dnombre;
		this.presu = presu;
		this.empleados=new ArrayList();
		this.proyectos=new ArrayList();
		
		
	}


	public Departamento(Integer codDept, String dnombre, Double presu) {
		
		this.codDept = codDept;
		this.dnombre = dnombre;
		this.presu = presu;
		this.empleados=new ArrayList();
		this.proyectos=new ArrayList();
	}
	
	
	Actualizamos y modificamos en departamento V1
	
}
