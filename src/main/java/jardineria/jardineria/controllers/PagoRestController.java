package jardineria.jardineria.controllers;

import java.util.Date;
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

import jardineria.jardineria.model.entity.Pago;
import jardineria.jardineria.model.service.pago.IPagoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class PagoRestController {
    
    public IPagoService pagoService;

    @GetMapping("pagos")
    public ResponseEntity<List<Pago>> getAllPagos() {
        return pagoService.searchPagos();
    }

    @PostMapping("pagos")
    public ResponseEntity<List<Pago>> savePago(@RequestParam("formaPago") String formaPago, @RequestParam("total") double total, @RequestParam("clienteId") Long clienteId) {
        Pago pago = new Pago();
        pago.setFormaPago(formaPago);
        pago.setFechaPago(new Date());
        pago.setTotal(total);
        return pagoService.savePago(pago, clienteId);
    }

    @PutMapping("pagos/{pagoId}")
    public ResponseEntity<List<Pago>> updatePago(@RequestParam("formaPago") String formaPago, @RequestParam("total") double total, @RequestParam("clienteId") Long clienteId, @PathVariable Long pagoId) {
        Pago pago = new Pago();
        pago.setFormaPago(formaPago);
        pago.setFechaPago(new Date());
        pago.setTotal(total);
        return pagoService.updatePago(pago, clienteId, pagoId);
    }

    @GetMapping("pagos/{id}")
    public ResponseEntity<Pago> searchPagoById(@PathVariable Long id) {
        return pagoService.searchPagoById(id);
    }

    @DeleteMapping("pagos/{id}")
    public ResponseEntity<List<Pago>> deletePago(@PathVariable Long id) {
        return pagoService.deletePago(id);
    }

}
