package jardineria.jardineria.model.service.oficina;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.Oficina;

public interface IOficinaService {
    public ResponseEntity<List<Oficina>> search();

    public ResponseEntity<List<Oficina>> save(Oficina oficina);

    public ResponseEntity<Oficina> searchById(Long id);

    public ResponseEntity<List<Oficina>> update(Oficina oficina,Long id);

    public ResponseEntity<List<Oficina>> delete(Long id);
}
