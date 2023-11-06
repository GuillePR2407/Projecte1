package com.example.demo.bean;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMERS")
public class Empresa {

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
	
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    
    
    Empresa() {}
    
    Empresa(String nombre, String descripcion) {

        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Empresa))
	      return false;
	    Empresa empresa = (Empresa) o;
	    return Objects.equals(this.id, empresa.id) && Objects.equals(this.nombre, empresa.nombre)
	        && Objects.equals(this.descripcion, empresa.descripcion);
	  }
	
	 @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.nombre, this.descripcion);
	  }

	  @Override
	  public String toString() {
	    return "Empresa{" + "id=" + this.id + ", nombre='" + this.nombre + '\'' + ", descripcion='" + this.descripcion + '\'' + '}';
	  }
    
}

