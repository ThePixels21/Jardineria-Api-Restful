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
@Table(name = "gama_producto")
public class GammaProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gama", length = 50,nullable = false)
    private String gamma;

    @Column(name = "descripcion_texto",nullable = true)
    private String descriptionText;

    @Column(name = "descripcion_html",nullable = true)
    private String descriptionHtml;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture", length = 5000,nullable = true)
    private byte[] picture;
}
