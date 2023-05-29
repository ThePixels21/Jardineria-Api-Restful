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

import jardineria.jardineria.model.entity.Cliente;
import jardineria.jardineria.model.service.cliente.IClienteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class ClienteRestController {
    
    public IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return clienteService.searchClientes();
    }

    @PostMapping("clientes")
    public ResponseEntity<List<Cliente>> saveCliente(@RequestParam Cliente cliente, @RequestParam("repVentasId") Long repVentasId) {
        return clienteService.saveCliente(cliente, repVentasId);
    }

    @PutMapping("clientes/{id}")
    public ResponseEntity<List<Cliente>> saveCliente(@RequestParam Cliente cliente, @RequestParam("repVentasId") Long repVentasId, @PathVariable Long clienteId) {
        return clienteService.updateCliente(cliente, repVentasId, clienteId);
    }

    @GetMapping("clientes/{id}")
    public ResponseEntity<Cliente> searchClienteById(@PathVariable Long id) {
        return clienteService.searchClienteById(id);
    }

    @DeleteMapping("clientes/{id}")
    public ResponseEntity<List<Cliente>> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }

}
