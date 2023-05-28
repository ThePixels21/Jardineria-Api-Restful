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
@RequestMapping("/api/")
public class EmpleadoRestController {
    
    public IEmpleadoService empleadoService;

    @GetMapping("empleados")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return empleadoService.searchEmpleados();
    }

    @PostMapping("empleados")
    public ResponseEntity<List<Empleado>> saveEmpleado(@RequestParam Empleado empleado, @RequestParam("oficinaId") Long oficinaId, @RequestParam("jefeId") Long jefeId) {
        return empleadoService.saveEmpleado(empleado, oficinaId, jefeId);
    }

    @PutMapping("empleados/{id}")
    public ResponseEntity<List<Empleado>> saveEmpleado(@RequestParam Empleado empleado, @RequestParam("oficinaId") Long oficinaId, @RequestParam("jefeId") Long jefeId, @PathVariable Long empleadoId) {
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
