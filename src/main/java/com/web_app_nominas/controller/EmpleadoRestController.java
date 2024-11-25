package com.web_app_nominas.controller;

import com.web_app_nominas.model.Empleado;
import com.web_app_nominas.service.EmpleadoService;
import com.web_app_nominas.exceptions.DatosNoCorrectosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    // Obtener un empleado por su DNI
    // Este tipo de cuerpo en la URL no se deberia de usar a causa de vulnerabilidad
    // esto es solo un ejemplo
    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> getEmpleadoByDni(@PathVariable String dni) {
        Optional<Empleado> empleado = empleadoService.obtenerEmpleadoPorDni(dni);
        if (empleado.isPresent()) {
            return new ResponseEntity<>(empleado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo empleado
    @PostMapping
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado newEmpleado = empleadoService.guardarEmpleado(empleado);
            return new ResponseEntity<>(newEmpleado, HttpStatus.CREATED);
        } catch (DatosNoCorrectosException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Datos no válidos
        }
    }

    // Actualizar un empleado existente
    @PutMapping("/{dni}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        try {
            boolean updated = empleadoService.actualizarEmpleado(dni, empleado.getNombre(), empleado.getSexo(), empleado.getCategoria(), empleado.getAnyos());
            if (updated) {
                return new ResponseEntity<>(empleado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DatosNoCorrectosException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Datos no válidos
        }
    }

    // Eliminar un empleado
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable String dni) {
        boolean deleted = empleadoService.eliminarEmpleado(dni);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No se encuentra el empleado
        }
    }

    // Obtener el salario de un empleado por su DNI
    @GetMapping("/{dni}/salario")
    public ResponseEntity<Double> getEmpleadoSalario(@PathVariable String dni) {
        Double salario = empleadoService.obtenerSalario(dni);
        if (salario != null) {
            return new ResponseEntity<>(salario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No se encuentra el salario o el empleado
        }
    }
}
