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
    public ResponseEntity<List<Cliente>> saveCliente(@RequestParam("nombre") String nombre, String nombreContacto, String apellidoContacto, @RequestParam("telefono") String telefono, @RequestParam("fax") String fax, @RequestParam("direccion1") String direccion1, String direccion2, @RequestParam("ciudad") String ciudad, String region, String pais, String codigoPostal, Long repVentasId, double limiteCredito) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setNombreContacto(nombreContacto);
        cliente.setApellidoContacto(apellidoContacto);
        cliente.setTelefono(telefono);
        cliente.setFax(fax);
        cliente.setDireccion1(direccion1);
        cliente.setDireccion2(direccion2);
        cliente.setCiudad(ciudad);
        cliente.setRegion(region);
        cliente.setPais(pais);
        cliente.setCodigoPostal(codigoPostal);
        cliente.setLimiteCredito(limiteCredito);
        return clienteService.saveCliente(cliente, repVentasId);
    }

    @PutMapping("clientes/{clienteId}")
    public ResponseEntity<List<Cliente>> updateCliente(@RequestParam("nombre") String nombre, String nombreContacto, String apellidoContacto, @RequestParam("telefono") String telefono, @RequestParam("fax") String fax, @RequestParam("direccion1") String direccion1, String direccion2, @RequestParam("ciudad") String ciudad, String region, String pais, String codigoPostal, Long repVentasId, double limiteCredito, @PathVariable Long clienteId) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setNombreContacto(nombreContacto);
        cliente.setApellidoContacto(apellidoContacto);
        cliente.setTelefono(telefono);
        cliente.setFax(fax);
        cliente.setDireccion1(direccion1);
        cliente.setDireccion2(direccion2);
        cliente.setCiudad(ciudad);
        cliente.setRegion(region);
        cliente.setPais(pais);
        cliente.setCodigoPostal(codigoPostal);
        cliente.setLimiteCredito(limiteCredito);
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
