package jardineria.jardineria.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado",
indexes = {@Index(name = "index_nombre_empleado",columnList = "nombre")})
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_empleado")
    private Long codigo;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido1;

    @Column(length = 50, nullable = true)
    private String apellido2;

    @Column(length = 10, nullable = false)
    private String extension;

    @Column(length = 100, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_oficina", referencedColumnName = "codigoOficina")
    private Oficina oficina;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_jefe", referencedColumnName = "codigo", nullable = true)
    private Empleado jefe;

    @Column(length = 50, nullable = true)
    private String puesto;

}