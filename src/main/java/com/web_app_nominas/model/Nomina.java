package com.web_app_nominas.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nominas")
public class Nomina {

    // Sueldos base según la categoría del empleado
    @Transient
    private static final int[] SUELDO_BASE = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Empleado empleado;

    @Column(name = "sueldo_final")
    private double sueldoFinal;

    // Constructor vacío requerido para JPA
    public Nomina() {}

    public Nomina(Empleado empleado) {
        this.empleado = empleado;
        this.sueldoFinal = calcularSueldo(empleado);
    }

    // Método para calcular el sueldo basado en la categoría y años trabajados del empleado
    public double calcularSueldo(Empleado emp) {
        int categoriaSueldo = emp.getCategoria();
        return SUELDO_BASE[categoriaSueldo - 1] + 5000 * emp.getAnyos();
    }

    // Método para actualizar el sueldoFinal en función de los datos del empleado
    public void actualizarSueldoFinal() {
        this.sueldoFinal = calcularSueldo(this.empleado);
    }

    public double getSueldoFinal() {
        return sueldoFinal;
    }

    public void setSueldoFinal(double sueldoFinal) {
        this.sueldoFinal = sueldoFinal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    // Recalcular sueldoFinal si el empleado cambia
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        actualizarSueldoFinal(); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nomina nomina = (Nomina) o;
        return Objects.equals(id, nomina.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Nomina{" +
                "id=" + id +
                ", empleado=" + empleado +
                ", sueldoFinal=" + sueldoFinal +
                '}';
    }
}
