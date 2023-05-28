package jardineria.jardineria.model.service.pago;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.Pago;

public interface IPagoService {
    
    public ResponseEntity<List<Pago>> searchPagos();

    public ResponseEntity<List<Pago>> savePago(Pago pago, Long clienteId);

    public ResponseEntity<List<Pago>> updatePago(Pago pago, Long clienteId, Long pagoId);

    public ResponseEntity<List<Pago>> deletePago(Long id);

    public ResponseEntity<Pago> searchPagoById(Long id);
    
}
