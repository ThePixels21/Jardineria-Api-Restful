package jardineria.jardineria.model.repository;

import org.springframework.data.repository.CrudRepository;

import jardineria.jardineria.model.entity.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {}
