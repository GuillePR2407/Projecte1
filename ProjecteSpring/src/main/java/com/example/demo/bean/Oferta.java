package com.example.demo.bean;

import java.util.Calendar;

import javax.persistence.*;


@Entity
@Table(name = "OFERTAS")
public class Oferta {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS", length = 20, nullable = false)
    private Status status;
    @Column(name = "NOM", length = 20, nullable = false)
    private String nom;

    @Column(name = "DESCRIPCIO", length = 20, nullable = false)
    private String descripcio;
    
    @Column(name = "Sector", length = 20, nullable = false)
    private Sector sector;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGIST_DATE", nullable = false)
    private Calendar registDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPRESA", nullable = false)
    private Empresa empresa;
	
    public Oferta(Status status, String nom, String descripcio, Sector sector, Calendar registDate, Empresa empresa) {
		super();
		this.status = status;
		this.nom = nom;
		this.descripcio = descripcio;
		this.sector = sector;
		this.registDate = registDate;
		this.empresa = empresa;
	}

	public Oferta() {

	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
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
	
	public Sector getSector() {
		return sector;
	}


	public void setSector(Sector sector) {
		this.sector = sector;
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
