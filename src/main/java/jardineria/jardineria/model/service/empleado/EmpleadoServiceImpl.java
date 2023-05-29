package jardineria.jardineria.model.service.empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jardineria.jardineria.model.entity.Empleado;
import jardineria.jardineria.model.entity.Oficina;
import jardineria.jardineria.model.repository.IEmpleadoRepository;
import jardineria.jardineria.model.repository.IOficinaRepository;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private IOficinaRepository oficinaRepository;

    private List<Empleado> empleados = new ArrayList<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Empleado>> searchEmpleados() {
        try {
            empleados = (List<Empleado>) empleadoRepository.findAll();
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(empleados, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Empleado>> saveEmpleado(Empleado empleado, Long oficinaId, Long jefeId) {
        try {

            if (jefeId != null) {
                Optional<Empleado> jefe = empleadoRepository.findById(jefeId);
                if (jefe.isPresent()) {
                    empleado.setJefe(jefe.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            Optional<Oficina> oficina = oficinaRepository.findById(oficinaId);
            if (oficina.isPresent()) {
                empleado.setOficina(oficina.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Empleado empleadoSaved = empleadoRepository.save(empleado);

            if (empleadoSaved != null) {
                empleados.add(empleadoSaved);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<Empleado>> updateEmpleado(Empleado empleado, Long oficinaId, Long jefeId, Long empleadoId) {
        try {

            if (jefeId != null) {
                Optional<Empleado> jefe = empleadoRepository.findById(jefeId);
                if (jefe.isPresent()) {
                    empleado.setJefe(jefe.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            Optional<Oficina> oficina = oficinaRepository.findById(oficinaId);
            if (oficina.isPresent()) {
                empleado.setOficina(oficina.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<Empleado> empleadoSearch = empleadoRepository.findById(empleadoId);
            if(empleadoSearch.isPresent()) {
                empleadoSearch.get().setNombre(empleado.getNombre());
                empleadoSearch.get().setApellido1(empleado.getApellido1());
                empleadoSearch.get().setApellido2(empleado.getApellido2());
                empleadoSearch.get().setExtension(empleado.getExtension());
                empleadoSearch.get().setEmail(empleado.getEmail());
                empleadoSearch.get().setOficina(empleado.getOficina());
                if(jefeId != null) {
                    empleadoSearch.get().setJefe(empleado.getJefe());
                }
                empleadoSearch.get().setPuesto(empleado.getPuesto());
                
                Empleado empleadoSaved = empleadoRepository.save(empleadoSearch.get());

                if(empleadoSaved!=null) {
                    empleados.add(empleadoSaved);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<Empleado>> deleteEmpleado(Long id) {
        try {
            empleadoRepository.deleteById(id);
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Empleado> searchEmpleadoById(Long id) {
        try {
            Optional<Empleado> empleado = empleadoRepository.findById(id);
            if(empleado.isPresent()) {
                return new ResponseEntity<>(empleado.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
