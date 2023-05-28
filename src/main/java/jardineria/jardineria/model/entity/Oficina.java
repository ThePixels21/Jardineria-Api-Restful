package jardineria.jardineria.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "oficina",
indexes = {@Index(name = "index_codigo_postal",columnList = "codigo_postal",unique = true)})
public class Oficina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_oficina")
    private Long codigoOficina;

    @Column(length = 30, nullable = false)
    private String ciudad;

    @Column(length = 50, nullable = false)
    private String pais;

    @Column(length = 50, nullable = true)
    private String region;

    @Column(name = "codigo_postal", length = 10, nullable = false)
    private String codigoPostal;

    @Column(length = 20, nullable = false)
    private String telefono;

    @Column(name = "linea_direccion1", length = 50, nullable = false)
    private String direccion1;

    @Column(name = "linea_direccion2", length = 50, nullable = true)
    private String direccion2;

}
