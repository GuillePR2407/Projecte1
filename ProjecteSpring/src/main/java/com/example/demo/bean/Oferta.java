package com.example.demo.bean;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "OFERTAS")
public class Oferta {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS", length = 20, nullable = false)
    private String status;

    @Column(name = "NOM", length = 20, nullable = false)
    private String nom;

    @Column(name = "DESCRIPCIO", length = 20, nullable = false)
    private String descripcio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGIST_DATE", nullable = false)
    private Calendar registDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPRESA", nullable = false)
    private Empresa empresa;
	
    public Oferta(){}

	public Oferta(String status, String nom, String descripcio, Calendar registDate, Empresa empresa) {
		super();
		this.status = status;
		this.nom = nom;
		this.descripcio = descripcio;
		this.registDate = registDate;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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


	public Long getEmpresa() {
		return empresa.getId();
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
     
}
