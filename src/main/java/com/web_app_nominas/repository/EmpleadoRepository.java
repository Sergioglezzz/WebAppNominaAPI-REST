package com.web_app_nominas.repository;

import com.web_app_nominas.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

    // Método para obtener lista de empleados (implementación proporcionada por JpaRepository)
    List<Empleado> findAll();

    // Método para obtener empleados filtrados, similar a obtenerEmpleadosFiltrados del DAO
    @Query("SELECT e FROM Empleado e WHERE " +
           "(:dni IS NULL OR e.dni LIKE %:dni%) AND " +
           "(:nombre IS NULL OR e.nombre LIKE %:nombre%) AND " +
           "(:sexo IS NULL OR e.sexo = :sexo) AND " +
           "(:categoria IS NULL OR e.categoria = :categoria) AND " +
           "(:anyos IS NULL OR e.anyos = :anyos)")
    List<Empleado> findFiltered(
            @Param("dni") String dni,
            @Param("nombre") String nombre,
            @Param("sexo") Character sexo,
            @Param("categoria") Integer categoria,
            @Param("anyos") Integer anyos);

    // Método para obtener un empleado por DNI
    Empleado findByDni(String dni);

    // Método para obtener el salario de un empleado por DNI
    @Query("SELECT n.sueldoFinal FROM Nomina n WHERE n.empleado.dni = :dni")
    Double findSueldoByDni(@Param("dni") String dni);

    // Método para modificar un campo específico de un empleado
    @Query("UPDATE Empleado e SET e.nombre = :nombre, e.sexo = :sexo, e.categoria = :categoria, e.anyos = :anyos WHERE e.dni = :dni")
    int updateEmpleado(
            @Param("dni") String dni,
            @Param("nombre") String nombre,
            @Param("sexo") Character sexo,
            @Param("categoria") int categoria,
            @Param("anyos") int anyos);
}
