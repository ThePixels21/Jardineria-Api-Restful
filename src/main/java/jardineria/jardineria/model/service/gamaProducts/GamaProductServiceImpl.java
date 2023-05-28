package jardineria.jardineria.model.service.gamaProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jardineria.jardineria.model.entity.GamaProduct;
import jardineria.jardineria.model.repository.IGamaProductRepository;

@Service
public class GamaProductServiceImpl implements IGamaProductService{

    private final IGamaProductRepository gamaProductRepository;

    List<GamaProduct> gamas = new ArrayList<>();

    public GamaProductServiceImpl (IGamaProductRepository gamaProductRepository) {
        this.gamaProductRepository = gamaProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<GamaProduct>> search() {
        try{
            gamas = (List<GamaProduct>) gamaProductRepository.findAll();
            return new ResponseEntity<>(gamas,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(gamas,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<GamaProduct>> save(GamaProduct gamaProduct) {
        try{
            GamaProduct gamaSaved = gamaProductRepository.save(gamaProduct);
            gamas.add(gamaSaved);
            return new ResponseEntity<>(gamas,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(gamas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GamaProduct> searchById(Long gama) {
        try{
            Optional<GamaProduct> gamas = gamaProductRepository.findById(gama);
            if(gamas.isPresent()){
                return new ResponseEntity<>(gamas.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<GamaProduct>> update(GamaProduct gamaProduct, Long gama) {
        try {
            Optional<GamaProduct> gamaSearch = gamaProductRepository.findById(gama);
            if(gamaSearch.isPresent()) {
                gamaSearch.get().setDescripcionTexto(gamaProduct.getDescripcionTexto());
                gamaSearch.get().setDescripcionHtml(gamaProduct.getDescripcionHtml());
                gamaSearch.get().setImagen(gamaProduct.getImagen());

                GamaProduct gamaSaved = gamaProductRepository.save(gamaSearch.get());

                if(gamaSaved!=null) {
                    gamas.add(gamaSaved);
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
    public ResponseEntity<List<GamaProduct>> delete(Long gama) {
        try {
            gamaProductRepository.deleteById(gama);
            return new ResponseEntity<>(gamas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
