package com.example.demo.bean;

import javax.persistence.*;

@Entity
@Table(name = "USUARIOS_ADMINISTRADORES")
public class Administrador {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "CORREO", nullable = false, length = 100)
    private String correo;

    @Column(name = "CLAVE", nullable = false, length = 100)
    private String clave;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
