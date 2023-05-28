package jardineria.jardineria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jardineria.jardineria.model.entity.GamaProduct;
import jardineria.jardineria.model.service.gamaProducts.IGamaProductService;
import jardineria.jardineria.util.Util;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class GamaProductRestController {
    public IGamaProductService gamaService;

    @GetMapping("gamas")
    @Transactional(readOnly = true)
    public ResponseEntity<List<GamaProduct>> getAllProducts() {
        return gamaService.search();
    }
    
    @PostMapping("gamas")
    @Transactional
    public ResponseEntity<List<GamaProduct>> saveProduct(@RequestParam("imagen") MultipartFile imagen, @RequestParam("descripcionTexto") String descripcionTexto, @RequestParam("descripcionHtml") String descripcionHtml) throws Exception {
        GamaProduct gama = new GamaProduct();
        gama.setDescripcionTexto(descripcionTexto);
        gama.setDescripcionHtml(descripcionHtml);
        gama.setImagen(Util.compressZLib(imagen.getBytes()));
        return gamaService.save(gama);
    }

    @PutMapping("gamas/{id}")
    @Transactional
    public ResponseEntity<List<GamaProduct>> updateProduct(@RequestParam("imagen") MultipartFile imagen, @RequestParam("descripcionTexto") String descripcionTexto, @RequestParam("descripcionHtml") String descripcionHtml, @PathVariable Long id) throws Exception {
        GamaProduct gama = new GamaProduct();
        gama.setDescripcionTexto(descripcionTexto);
        gama.setDescripcionHtml(descripcionHtml);
        gama.setImagen(Util.compressZLib(imagen.getBytes()));
        return gamaService.update(gama, id);
    }

    @GetMapping("gamas/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<GamaProduct> searchProductById(@PathVariable Long id) {
        return gamaService.searchById(id);
    }

    @DeleteMapping("gamas/{id}")
    @Transactional
    public ResponseEntity<List<GamaProduct>> deleteProduct(@PathVariable Long id) {
        return gamaService.delete(id);
    }
}
