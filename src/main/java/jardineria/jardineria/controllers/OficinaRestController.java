package jardineria.jardineria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jardineria.jardineria.model.entity.Oficina;
import jardineria.jardineria.model.service.oficina.IOficinaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class OficinaRestController {
    
    private IOficinaService oficinaService;

    @GetMapping("oficinas")
    public ResponseEntity<List<Oficina>> searchOficinas(){
        return oficinaService.search();
    }

    @PostMapping("oficinas")
    public ResponseEntity<List<Oficina>> saveOficina(@RequestBody Oficina oficina){
        return oficinaService.save(oficina);
    }

    @GetMapping("oficinas/{id}")
    public ResponseEntity<Oficina> searchOficinaById(@PathVariable Long id) {
        return oficinaService.searchById(id);
    }

    @PutMapping("oficinas/{id}")
    public ResponseEntity<List<Oficina>> updateOficina(@RequestBody Oficina oficina, @PathVariable Long id) {
        return oficinaService.update(oficina, id);
    }

    @DeleteMapping("oficinas/{id}")
    public ResponseEntity<List<Oficina>> deleteOficina(@PathVariable Long id){
        return oficinaService.delete(id);
    }
}
