package jardineria.jardineria.model.service.gamaProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jardineria.jardineria.model.entity.GammaProduct;
import jardineria.jardineria.model.repository.IGammaProductRepository;

@Service
public class GammaProductServiceImpl implements IGammaProductService{

    private final IGammaProductRepository gamaProductRepository;

    List<GammaProduct> gamas = new ArrayList<>();

    public GammaProductServiceImpl (IGammaProductRepository gamaProductRepository) {
        this.gamaProductRepository = gamaProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<GammaProduct>> search() {
        try{
            gamas = (List<GammaProduct>) gamaProductRepository.findAll();
            return new ResponseEntity<>(gamas,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(gamas,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<GammaProduct>> save(GammaProduct gamaProduct) {
        try{
            GammaProduct gamaSaved = gamaProductRepository.save(gamaProduct);
            gamas.add(gamaSaved);
            return new ResponseEntity<>(gamas,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(gamas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<GammaProduct> searchById(Long gama) {
        try{
            Optional<GammaProduct> gamas = gamaProductRepository.findById(gama);
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
    public ResponseEntity<List<GammaProduct>> update(GammaProduct gamaProduct, Long gama) {
        try {
            Optional<GammaProduct> gamaSearch = gamaProductRepository.findById(gama);
            if(gamaSearch.isPresent()) {
                gamaSearch.get().setDescriptionText(gamaProduct.getDescriptionText());
                gamaSearch.get().setDescriptionHtml(gamaProduct.getDescriptionHtml());
                gamaSearch.get().setPicture(gamaProduct.getPicture());

                GammaProduct gamaSaved = gamaProductRepository.save(gamaSearch.get());

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
    public ResponseEntity<List<GammaProduct>> delete(Long gama) {
        try {
            gamaProductRepository.deleteById(gama);
            return new ResponseEntity<>(gamas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
