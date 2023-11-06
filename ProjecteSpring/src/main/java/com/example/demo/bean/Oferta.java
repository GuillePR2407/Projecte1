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
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "REGIST_DATE", nullable = false)
	    private Calendar registDate;
	    
	    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private Empresa empresa;

	    Oferta() {}
		
	    Oferta(long id, String status, Calendar registDate, Empresa empresa) {
			super();
			this.id = id;
			this.status = status;
			this.registDate = registDate;
			this.empresa = empresa;
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

		public Calendar getRegistDate() {
			return registDate;
		}

		public void setRegistDate(Calendar registDate) {
			this.registDate = registDate;
		}

		public Empresa getEmpresa() {
			return empresa;
		}

		public void setEmpresa(Empresa empresa) {
			this.empresa = empresa;
		}
	    
}
