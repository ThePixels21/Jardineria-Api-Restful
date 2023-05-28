package jardineria.jardineria.model.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "gama_product")
public class GammaProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gama", length = 50)
    private Long gamma;

    @Column(name = "descripcion_texto")
    private String descriptionText;

    @Column(name = "descripcion_html")
    private String descriptionHtml;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", length = 5000)
    private byte[] picture;
}