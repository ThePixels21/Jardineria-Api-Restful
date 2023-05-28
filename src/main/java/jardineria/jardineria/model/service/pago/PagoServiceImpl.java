package jardineria.jardineria.model.service.pago;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jardineria.jardineria.model.entity.Cliente;
import jardineria.jardineria.model.entity.Pago;
import jardineria.jardineria.model.repository.IClienteRepository;
import jardineria.jardineria.model.repository.IPagoRepository;

@Service
public class PagoServiceImpl implements IPagoService {

    @Autowired
    private IPagoRepository pagoRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    private List<Pago> pagos = new ArrayList<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Pago>> searchPagos() {
        try {
            pagos = (List<Pago>) pagoRepository.findAll();
            return new ResponseEntity<>(pagos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(pagos, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Pago>> savePago(Pago pago, Long clienteId) {
        try {

            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            if (cliente.isPresent()) {
                pago.setCliente(cliente.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Pago pagoSaved = pagoRepository.save(pago);

            if (pagoSaved != null) {
                pagos.add(pagoSaved);
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
    public ResponseEntity<List<Pago>> updatePago(Pago pago, Long clienteId, Long pagoId) {
        try {

            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            if (cliente.isPresent()) {
                pago.setCliente(cliente.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<Pago> pagoSearch = pagoRepository.findById(pagoId);
            if (pagoSearch.isPresent()) {
                pagoSearch.get().setFormaPago(pago.getFormaPago());
                pagoSearch.get().setFechaPago(pago.getFechaPago());
                pagoSearch.get().setTotal(pago.getTotal());
                pagoSearch.get().setCliente(pago.getCliente());

                Pago pagoSaved = pagoRepository.save(pagoSearch.get());

                if (pagoSaved != null) {
                    pagos.add(pagoSaved);
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
    public ResponseEntity<List<Pago>> deletePago(Long id) {
        try {
            pagoRepository.deleteById(id);
            return new ResponseEntity<>(pagos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Pago> searchPagoById(Long id) {
        try {
            Optional<Pago> pago = pagoRepository.findById(id);
            if(pago.isPresent()) {
                return new ResponseEntity<>(pago.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
