package jardineria.jardineria.model.service.oficina;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jardineria.jardineria.model.entity.Oficina;
import jardineria.jardineria.model.repository.IOficinaRepository;

@Service
public class OficinaServiceImpl implements IOficinaService {

    @Autowired
    private IOficinaRepository oficinaRepository;

    public List<Oficina> oficinas = new ArrayList<>();

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Oficina>> search() {
        try{
            oficinas = (List<Oficina>) oficinaRepository.findAll();
            return new ResponseEntity<>(oficinas, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(oficinas, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Oficina>> save(Oficina oficina) {
        try{
            Oficina oficinaSaved = oficinaRepository.save(oficina);
            oficinas.add(oficinaSaved);
            return new ResponseEntity<>(oficinas,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(oficinas,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Oficina> searchById(Long id) {
        try{
            Optional<Oficina> oficina = oficinaRepository.findById(id);
            if(oficina.isPresent()){
                return new ResponseEntity<>(oficina.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Oficina>> update(Oficina oficina, Long id) {
        try {
            Optional<Oficina> oficinaSearch = oficinaRepository.findById(id);
            if(oficinaSearch.isPresent()){
                oficinaSearch.get().setCiudad(oficina.getCiudad());
                oficinaSearch.get().setPais(oficina.getPais());
                oficinaSearch.get().setRegion(oficina.getRegion());
                oficinaSearch.get().setCodigoPostal(oficina.getCodigoPostal());
                oficinaSearch.get().setTelefono(oficina.getTelefono());
                oficinaSearch.get().setDireccion1(oficina.getDireccion1());
                oficinaSearch.get().setDireccion2(oficina.getDireccion2());
                Oficina oficinaToUpdate = oficinaRepository.save(oficinaSearch.get());
                oficinas.add(oficinaToUpdate);
                return new ResponseEntity<>(oficinas,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<List<Oficina>> delete(Long id) {
        try {
            oficinaRepository.deleteById(id);
            return new ResponseEntity<>(oficinas,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
