package com.web_app_nominas.service;

import com.web_app_nominas.model.Empleado;
import com.web_app_nominas.repository.EmpleadoRepository;
import com.web_app_nominas.exceptions.DatosNoCorrectosException;
// import com.web_app_nominas.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorDni(String dni) {
        return empleadoRepository.findById(dni);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) throws DatosNoCorrectosException {
        // Realiza validaciones y guarda el empleado
        return empleadoRepository.save(empleado);
    }

    @Override
    public boolean actualizarEmpleado(String dni, String nombre, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(dni);
        if (empleadoExistente.isPresent()) {
            Empleado empleado = empleadoExistente.get();
            empleado.setNombre(nombre);
            empleado.setSexo(sexo);
            empleado.setCategoria(categoria);
            empleado.setAnyos(anyos);
            empleadoRepository.save(empleado);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarEmpleado(String dni) {
        if (empleadoRepository.existsById(dni)) {
            empleadoRepository.deleteById(dni);
            return true;
        }
        return false;
    }

    @Override
    public Double obtenerSalario(String dni) {
        // Implementación para obtener el salario de la tabla "nominas"
        return empleadoRepository.findSueldoByDni(dni);
    }
}

