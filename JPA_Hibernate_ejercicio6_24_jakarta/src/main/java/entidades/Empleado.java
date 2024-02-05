package entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="EMPLEADO", catalog = "ejercicio6", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODEMPLEADO"),@UniqueConstraint(columnNames = "DIRECCION_FK")
})

//consultas estáticas
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String BUSCAR_TODOS_EMPLEADOS="Empleado.findAll";
	
	@Id
	@Column(name = "CODEMPLEADO", unique = true, nullable = false)
	private String codEmpleado;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDOS")
    private String apellidos;
	
	//3.1  Asociación unidireccional ONE to ONE sobre Empleado y  Direccion
	
	//para añadir restriccion hay que especificar , unique=true, nullable=false, updatable=false) 
	//sino con anotar la restricción en table es suficiente 
	@OneToOne
	@JoinColumn(name="DIRECCION_FK", unique=true, nullable=false, updatable=false)
	private Direccion dir;
		
		
		
		
	//3.3 Asociación bidireccional ONE to ONE sobre Empleado y PlazaParking
	@OneToOne (targetEntity=PlazaParking.class)
	@JoinColumn(name="PLAZA_FK", unique=true, nullable = false,  updatable=false)
	private PlazaParking plaza;
	
	
	
	/* Cuando JPA realice el mapeo de esta relación, cada entidad será almacenada en su propia tabla,
	 * añadiendo a la tabla donde se almacenan los empleados (la dueña de la relación) una columna 
	 * con las claves foráneas necesarias para asociar cada fila con la fila correspondiente 
	 * en la tabla donde se almacenan las direcciones. Recuerda que JPA utiliza configuración por defecto
	 * para realizar el mapeo, pero podemos customizar este proceso definiendo el nombre de la columna
	 * que contendrá la clave foránea mediante la anotación @JoinColumn
	 *    @OneToOne 
		  @JoinColumn(name = "DIRECCION_FK") 
          private Direccion direccion;   */
	
	
   
	
	
    public Empleado() {
	}
    
    public Empleado(String codEmpleado, String nombre, String apellidos, Direccion dir ) {
		this.codEmpleado=codEmpleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dir = dir;
		
	}
	public Empleado(String codEmpleado, String nombre, String apellidos, Direccion dir,PlazaParking p ) {
		this.codEmpleado=codEmpleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dir = dir;
		this.plaza = p;
	}

	public String getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(String codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Direccion getDir() {
		return dir;
	}

	public void setDir(Direccion dir) {
		this.dir = dir;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEmpleado == null) ? 0 : codEmpleado.hashCode());
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
		Empleado other = (Empleado) obj;
		if (codEmpleado == null) {
			if (other.codEmpleado != null)
				return false;
		} else if (!codEmpleado.equals(other.codEmpleado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado ["+ codEmpleado + "," + nombre + "," + apellidos + "," + plaza + "," + dir + "]";
	}

	public PlazaParking getPlaza() {
		return plaza;
	}

	public void setPlaza(PlazaParking plaza) {
		this.plaza = plaza;
	}
    
    
    
}
