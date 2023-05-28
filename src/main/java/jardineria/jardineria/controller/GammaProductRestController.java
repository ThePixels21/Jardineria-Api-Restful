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

import jardineria.jardineria.model.entity.GammaProduct;
import jardineria.jardineria.model.service.gamaProducts.IGammaProductService;
import jardineria.jardineria.util.Util;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class GammaProductRestController {
    public IGammaProductService gamaService;

    @GetMapping("gammas")
    @Transactional(readOnly = true)
    public ResponseEntity<List<GammaProduct>> getAllProducts() {
        return gamaService.search();
    }
    
    @PostMapping("gammas")
    @Transactional
    public ResponseEntity<List<GammaProduct>> saveProduct(@RequestParam("picture") MultipartFile picture, @RequestParam("descriptionText") String descriptionText, @RequestParam("descriptionHtml") String descriptionHtml) throws Exception {
        GammaProduct gama = new GammaProduct();
        gama.setDescriptionText(descriptionText);
        gama.setDescriptionHtml(descriptionHtml);
        gama.setPicture(Util.compressZLib(picture.getBytes()));
        return gamaService.save(gama);
    }

    @PutMapping("gammas/{id}")
    @Transactional
    public ResponseEntity<List<GammaProduct>> updateProduct(@RequestParam("picture") MultipartFile picture, @RequestParam("descriptionText") String descriptionText, @RequestParam("descriptionHtml") String descriptionHtml, @PathVariable Long id) throws Exception {
        GammaProduct gama = new GammaProduct();
        gama.setDescriptionText(descriptionText);
        gama.setDescriptionHtml(descriptionHtml);
        gama.setPicture(Util.compressZLib(picture.getBytes()));
        return gamaService.update(gama, id);
    }

    @GetMapping("gammas/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<GammaProduct> searchProductById(@PathVariable Long id) {
        return gamaService.searchById(id);
    }

    @DeleteMapping("gammas/{id}")
    @Transactional
    public ResponseEntity<List<GammaProduct>> deleteProduct(@PathVariable Long id) {
        return gamaService.delete(id);
    }
}
