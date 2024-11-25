package com.web_app_nominas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona {

    @Column(name = "categoria")
    @Min(value = 1, message = "La categoría debe ser como mínimo 1")
    @Max(value = 10, message = "La categoría debe ser como máximo 10")
    private int categoria = 1;

    @Column(name = "anyos")
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private int anyos = 0;

    public Empleado() {
        super();
    }

    public Empleado(String dni, String nombre, char sexo, int categoria, int anyos) {
        super(nombre, dni, sexo);
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public Empleado(String dni, String nombre, char sexo) {
        super(nombre, dni, sexo);
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        if (categoria < 1 || categoria > 10) {
            throw new IllegalArgumentException("Categoría incorrecta, debe ser entre 1 y 10");
        }
        this.categoria = categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        if (anyos < 0) {
            throw new IllegalArgumentException("Los años no pueden ser negativos");
        }
        this.anyos = anyos;
    }

    public void incrAnyo() {
        this.anyos++;
    }

    @Override
    public String toString() {
        return "Empleado [dni=" + dni + ", nombre=" + nombre + ", sexo=" + sexo + ", categoria=" + categoria + ", anyos=" + anyos + "]";
    }
}
