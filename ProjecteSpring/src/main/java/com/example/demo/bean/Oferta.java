package com.example.demo.bean;

import java.util.Calendar;
import jakarta.persistence.*;
import jakarta.transaction.Status;


@Entity
@Table(name="OFERTAS")
public class Oferta {
	 @Id
	    @Column(name="ID")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    
	    @Column(name = "STATUS", length = 20, nullable = false)
	    @Enumerated(EnumType.STRING)
	    private Status status;
	    
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "REGIST_DATE", nullable = false)
	    private Calendar registDate;
	    
	    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    private Empresa empresa;

	    Oferta() {}
		
	    Oferta(long id, Status status, Calendar registDate, Empresa empresa) {
			super();
			this.id = id;
			this.status = status;
			this.registDate = registDate;
			this.empresa = empresa;
		}
	    
	    
}
