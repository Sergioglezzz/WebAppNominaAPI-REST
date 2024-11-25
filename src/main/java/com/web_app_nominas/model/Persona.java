package com.web_app_nominas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public class Persona {

    @Id
    @Column(name = "dni", unique = true, nullable = false, length = 20)
    @NotNull(message = "El DNI no puede ser nulo")
    @Size(min = 1, max = 20, message = "El DNI debe tener entre 1 y 20 caracteres")
    protected String dni;
    
    @Column(name = "nombre", nullable = false)
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
    protected String nombre;


    @Column(name = "sexo", nullable = false, length = 1)
    protected char sexo;

    // Constructor vacío necesario para JPA
    public Persona() {}

    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Persona [dni=" + dni + ", nombre=" + nombre +  ", sexo=" + sexo + "]";
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
