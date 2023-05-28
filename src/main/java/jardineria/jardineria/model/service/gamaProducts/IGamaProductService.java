package jardineria.jardineria.model.service.gamaProducts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.GamaProduct;

public interface IGamaProductService {
    public ResponseEntity<List<GamaProduct>> search();

    public ResponseEntity<List<GamaProduct>> save(GamaProduct gamaProduct);

    public ResponseEntity<GamaProduct> searchById(Long gama);

    public ResponseEntity<List<GamaProduct>> update(GamaProduct categorygamaProduct,Long gama);

    public ResponseEntity<List<GamaProduct>> delete(Long gama);
}
