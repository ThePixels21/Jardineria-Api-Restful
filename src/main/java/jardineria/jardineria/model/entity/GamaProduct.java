package jardineria.jardineria.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "gama-product")
public class GamaProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gama", length = 50)
    private Long gama;

    @Column(name = "descripcion_texto")
    private String descripcionTexto;

    @Column(name = "descripcion_html")
    private String descripcionHtml;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagen", length = 5000)
    private byte[] imagen;
}
