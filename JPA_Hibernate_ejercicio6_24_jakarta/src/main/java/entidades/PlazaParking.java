package entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="PLAZAPARKING", catalog = "ejercicio6", uniqueConstraints = {
		@UniqueConstraint(columnNames = "NUMPLAZA")
})
public class PlazaParking implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NUMPLAZA", unique = true, nullable = false)
	private String numPlaza; 
	
	
	//3.3 Asociación bidireccional ONE to ONE sobre Empleado y PlazaParking
	//mappedBy plaza es un atributo de Empleado donde se indica la plaza
	@OneToOne ( mappedBy = "plaza", targetEntity=Empleado.class)
	@JoinColumn( unique=true, nullable = false,  updatable=false)
	private Empleado empleado;
	
	
	
	@Column(name = "PLANTA")
	private Integer planta;
	
	@Column(name = "LUGAR")
    private Integer lugar;
	  
	
    public PlazaParking() {
	}


	public PlazaParking(String numPlaza,  Integer planta, Integer lugar) {
		
		this.numPlaza = numPlaza;
		this.planta = planta;
		this.lugar = lugar;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empleado == null) ? 0 : empleado.hashCode());
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
		PlazaParking other = (PlazaParking) obj;
		if (empleado == null) {
			if (other.empleado != null)
				return false;
		} else if (!empleado.equals(other.empleado))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PlazaParking ["+ numPlaza +"]";
	}


	public String getNumPlaza() {
		return numPlaza;
	}


	public void setNumPlaza(String numPlaza) {
		this.numPlaza = numPlaza;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}


	public Integer getPlanta() {
		return planta;
	}


	public void setPlanta(Integer planta) {
		this.planta = planta;
	}


	public Integer getLugar() {
		return lugar;
	}


	public void setLugar(Integer lugar) {
		this.lugar = lugar;
	}
   
   
    
    
    
}
