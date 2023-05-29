package jardineria.jardineria.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cliente")
    private Long codigo;

    @Column(name = "nombre_cliente", length = 50, nullable = false)
    private String nombre;

    @Column(name = "nombre_contacto", length = 30, nullable = true)
    private String nombreContacto;

    @Column(name = "apellido_contacto", length = 30, nullable = true)
    private String apellidoContacto;

    @Column(length = 15, nullable = false)
    private String telefono;
    
    @Column(length = 15, nullable = false)
    private String fax;

    @Column(name = "linea_direccion1", length = 50, nullable = false)
    private String direccion1;

    @Column(name = "linea_direccion2", length = 50, nullable = true)
    private String direccion2;

    @Column(length = 50, nullable = false)
    private String ciudad;

    @Column(length = 50, nullable = true)
    private String region;

    @Column(length = 50, nullable = true)
    private String pais;

    @Column(name = "codigo_postal", length = 10, nullable = true)
    private String codigoPostal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_empleado_rep_ventas", referencedColumnName = "codigo_empleado", nullable = true)
    private Empleado repVentas;

    @Column(name = "limite_credito", nullable = true)
    private double limiteCredito;

}
