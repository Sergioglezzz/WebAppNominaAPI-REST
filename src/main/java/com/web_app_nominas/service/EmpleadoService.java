package com.web_app_nominas.service;

import com.web_app_nominas.model.Empleado;
import com.web_app_nominas.exceptions.DatosNoCorrectosException;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    List<Empleado> obtenerTodosEmpleados();
    Optional<Empleado> obtenerEmpleadoPorDni(String dni);
    Empleado guardarEmpleado(Empleado empleado) throws DatosNoCorrectosException; // Método de guardar
    boolean actualizarEmpleado(String dni, String nombre, char sexo, int categoria, int anyos) throws DatosNoCorrectosException;
    boolean eliminarEmpleado(String dni); // Método de eliminar
    Double obtenerSalario(String dni);

            
}
