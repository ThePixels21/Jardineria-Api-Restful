package jardineria.jardineria.model.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pago",
indexes = {@Index(name = "index_forma_pago", columnList = "forma_pago")})
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;

    @Column(name = "forma_pago", length = 40, nullable = false)
    private String formaPago;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_pago", nullable = false)
    private Date fechaPago;

    @Column(nullable = false)
    private double total;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cliente", nullable = false)
    private Cliente cliente;

}
