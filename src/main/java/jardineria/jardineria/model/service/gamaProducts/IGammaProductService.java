package jardineria.jardineria.model.service.gamaProducts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import jardineria.jardineria.model.entity.GammaProduct;

public interface IGammaProductService {
    public ResponseEntity<List<GammaProduct>> search();

    public ResponseEntity<List<GammaProduct>> save(GammaProduct gamaProduct);

    public ResponseEntity<GammaProduct> searchById(Long gama);

    public ResponseEntity<List<GammaProduct>> update(GammaProduct gamaProduct,Long gama);

    public ResponseEntity<List<GammaProduct>> delete(Long gama);
}
