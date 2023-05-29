package jardineria.jardineria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jardineria.jardineria.model.entity.Empleado;
import jardineria.jardineria.model.service.empleado.IEmpleadoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class EmpleadoRestController {
    
    public IEmpleadoService empleadoService;

    @GetMapping("empleados")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return empleadoService.searchEmpleados();
    }

    @PostMapping("empleados")
    public ResponseEntity<List<Empleado>> saveEmpleado(@RequestParam("nombre") String nombre, @RequestParam("apellido1") String apellido1, String apellido2, @RequestParam("extension") String extension, @RequestParam("email") String email, @RequestParam("puesto") String puesto, @RequestParam("oficinaId") Long oficinaId, Long jefeId) {
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido1(apellido1);
        empleado.setApellido2(apellido2);
        empleado.setExtension(extension);
        empleado.setEmail(email);
        empleado.setPuesto(puesto);
        return empleadoService.saveEmpleado(empleado, oficinaId, jefeId);
    }

    @PutMapping("empleados/{empleadoId}")
    public ResponseEntity<List<Empleado>> saveEmpleado(@RequestParam("nombre") String nombre, @RequestParam("apellido1") String apellido1, String apellido2, @RequestParam("extension") String extension, @RequestParam("email") String email, @RequestParam("puesto") String puesto, @RequestParam("oficinaId") Long oficinaId, Long jefeId, @PathVariable Long empleadoId) {
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido1(apellido1);
        empleado.setApellido2(apellido2);
        empleado.setExtension(extension);
        empleado.setEmail(email);
        empleado.setPuesto(puesto);
        return empleadoService.updateEmpleado(empleado, oficinaId, jefeId, empleadoId);
    }

    @GetMapping("empleados/{id}")
    public ResponseEntity<Empleado> searchEmpleadoById(@PathVariable Long id) {
        return empleadoService.searchEmpleadoById(id);
    }

    @DeleteMapping("empleados/{id}")
    public ResponseEntity<List<Empleado>> deleteEmpleado(@PathVariable Long id) {
        return empleadoService.deleteEmpleado(id);
    }
    
}
