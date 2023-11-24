package com.example.demo.bean;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "EMPRESAS")
public class Empresa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Oferta> ofertas = new ArrayList<>();

    
    public void addOferta(Oferta oferta) {
        ofertas.add(oferta);
        oferta.setEmpresa(this);
    }

    public void removeOferta(Oferta oferta) {
        ofertas.remove(oferta);
        oferta.setEmpresa(null);
    }
   
    public Empresa() {}
    
	public Empresa(String nombre, String descripcion) {
		super();
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

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
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

