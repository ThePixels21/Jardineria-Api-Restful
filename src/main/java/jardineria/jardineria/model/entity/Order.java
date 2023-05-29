package jardineria.jardineria.model.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_pedido")
    private Long id;

    @Column(name = "fecha_pedido")
    @NotNull
    private Date orderDate;

    @Column(name = "fecha_esperada")
    @NotNull
    private Date waitDate;

    @Column(name = "fecha_entrega")
    private Date deadline;

    @Column(name = "estado",length = 15)
    private String status;

    @Column(name = "comentarios")
    private String coments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_empleado_rep_ventas", referencedColumnName = "codigo_empleado", nullable = true)
    private Cliente customer;



}
