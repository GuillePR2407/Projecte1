package com.example.demo.bean;

import java.util.Calendar;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="OFERTAS")
public class Oferta {
	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	 
	@Column(name = "STATUS", length = 20, nullable = false)
	private String status;
	 
 	@Column(name = "NOM", length = 20, nullable = false)
    private String nom;
 	
 	@Column(name = "DESCRIPCIO", length = 20, nullable = false)
    private String descripcio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGIST_DATE", nullable = false)
    private Calendar registDate;
    
    @Column(name = "ID_EMPRESA", nullable = false)
    private Long empresaId;

	
    Oferta(){}
    
    Oferta(String status, String nom, String descripcio, Calendar registDate, Long empresaId) {
		super();
		this.status = status;
		this.nom = nom;
		this.descripcio = descripcio;
		this.registDate = registDate;
		this.empresaId = empresaId;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescripcio() {
		return descripcio;
	}


	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}


	public Calendar getRegistDate() {
		return registDate;
	}


	public void setRegistDate(Calendar registDate) {
		this.registDate = registDate;
	}


	public Long getidEmpresa() {
		return empresaId;
	}


	public void setidEmpresa(Long EmpresaId) {
		this.empresaId = EmpresaId;
	}
        
}
