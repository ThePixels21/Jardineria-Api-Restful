package jardineria.jardineria.model.service.empleado;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.Empleado;

public interface IEmpleadoService {
    
    public ResponseEntity<List<Empleado>> searchEmpleados();

    public ResponseEntity<List<Empleado>> saveEmpleado(Empleado empleado, Long oficinaId, Long jefeId);

    public ResponseEntity<List<Empleado>> updateEmpleado(Empleado empleado, Long oficinaId, Long jefeId, Long empleadoId);

    public ResponseEntity<List<Empleado>> deleteEmpleado(Long id);

    public ResponseEntity<Empleado> searchEmpleadoById(Long id);
    
}
