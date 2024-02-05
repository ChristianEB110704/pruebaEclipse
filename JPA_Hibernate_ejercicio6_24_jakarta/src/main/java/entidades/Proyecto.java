package entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="PROYECTO", catalog = "ejercicio6", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODPROY")
})

public class Proyecto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "CODPROY", unique = true, nullable = false)
	private Integer codProy;
	
	@Column(name = "PNOMBRE")
	private String pnombre;
	
	//3.5 Asociación bidireccional ONE to MANY -Many To One sobre Departamento y Proyecto.
		@ManyToOne(targetEntity=Departamento.class)
		//@JoinColumn(name="CODDEPTO_FK",   updatable=false) //falla
		//@JoinColumn(referencedColumnName = "CODDEPT") // OPCIONAL hace falta indicarle la columna de BD con la que se relaciona
		private Departamento codDept;

	public Proyecto( Integer codProy,String pnombre) {
		this.pnombre = pnombre;
		this.codProy = codProy;
	}

	public Proyecto() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProy == null) ? 0 : codProy.hashCode());
		result = prime * result + ((pnombre == null) ? 0 : pnombre.hashCode());
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
		Proyecto other = (Proyecto) obj;
		if (codProy == null) {
			if (other.codProy != null)
				return false;
		} else if (!codProy.equals(other.codProy))
			return false;
		if (pnombre == null) {
			if (other.pnombre != null)
				return false;
		} else if (!pnombre.equals(other.pnombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proyecto [codProy=" + codProy + ", pnombre=" + pnombre + "]";
	}

	public Integer getCodProy() {
		return codProy;
	}

	public void setCodProy(Integer codProy) {
		this.codProy = codProy;
	}

	public String getPnombre() {
		return pnombre;
	}

	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}

	public Proyecto(Integer codP,String pnombre, Departamento codDept) {
		
		this.pnombre = pnombre;
		this.codDept = codDept;
		this.codProy=codP;
	}

	public Departamento getCodDept() {
		return codDept;
	}

	public void setCodDept(Departamento codDept) {
		this.codDept = codDept;
	}
	
	
	
}
