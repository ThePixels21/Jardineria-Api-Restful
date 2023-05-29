package jardineria.jardineria.model.service.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jardineria.jardineria.model.entity.Cliente;
import jardineria.jardineria.model.entity.Empleado;
import jardineria.jardineria.model.repository.IClienteRepository;
import jardineria.jardineria.model.repository.IEmpleadoRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    private List<Cliente> clientes = new ArrayList<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Cliente>> searchClientes() {
        try {
            clientes = (List<Cliente>) clienteRepository.findAll();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(clientes, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Cliente>> saveCliente(Cliente cliente, Long repVentasId) {
        try {

            if (repVentasId != null) {
                Optional<Empleado> repVentas = empleadoRepository.findById(repVentasId);
                if (repVentas.isPresent()) {
                    cliente.setRepVentas(repVentas.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

            Cliente clienteSaved = clienteRepository.save(cliente);

            if (clienteSaved != null) {
                clientes.add(clienteSaved);
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
    public ResponseEntity<List<Cliente>> updateCliente(Cliente cliente, Long repVentasId, Long clienteId) {
        try {

            if (repVentasId != null) {
                Optional<Empleado> repVentas = empleadoRepository.findById(repVentasId);
                if (repVentas.isPresent()) {
                    cliente.setRepVentas(repVentas.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                cliente.setRepVentas(null);
            }

            Optional<Cliente> clienteSearch = clienteRepository.findById(clienteId);
            if(clienteSearch.isPresent()) {
                clienteSearch.get().setNombre(cliente.getNombre());
                clienteSearch.get().setNombreContacto(cliente.getNombreContacto());
                clienteSearch.get().setApellidoContacto(cliente.getApellidoContacto());
                clienteSearch.get().setTelefono(cliente.getTelefono());
                clienteSearch.get().setFax(cliente.getFax());
                clienteSearch.get().setDireccion1(cliente.getDireccion1());
                clienteSearch.get().setDireccion2(cliente.getDireccion2());
                clienteSearch.get().setCiudad(cliente.getCiudad());
                clienteSearch.get().setRegion(cliente.getRegion());
                clienteSearch.get().setPais(cliente.getPais());
                clienteSearch.get().setCodigoPostal(cliente.getCodigoPostal());
                clienteSearch.get().setRepVentas(cliente.getRepVentas());
                clienteSearch.get().setLimiteCredito(cliente.getLimiteCredito());

                Cliente clienteSaved = clienteRepository.save(clienteSearch.get());

                if(clienteSaved!=null) {
                    clientes.add(clienteSaved);
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
    public ResponseEntity<List<Cliente>> deleteCliente(Long id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Cliente> searchClienteById(Long id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if(cliente.isPresent()) {
                return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
