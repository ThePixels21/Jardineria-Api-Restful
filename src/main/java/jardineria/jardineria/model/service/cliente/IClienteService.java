package jardineria.jardineria.model.service.cliente;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.Cliente;

public interface IClienteService {
    
    public ResponseEntity<List<Cliente>> searchClientes();

    public ResponseEntity<List<Cliente>> saveCliente(Cliente cliente, Long repVentasId);

    public ResponseEntity<List<Cliente>> updateCliente(Cliente cliente, Long repVentasId, Long clienteId);

    public ResponseEntity<List<Cliente>> deleteCliente(Long id);

    public ResponseEntity<Cliente> searchClienteById(Long id);
    
}
