package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.Empleado;

public interface IEmpleadoRepository extends CrudRepository<Empleado, Long> {}
