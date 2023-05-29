package jardineria.jardineria.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_pedido")
public class OrderDetails {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "codigo_producto", referencedColumnName = "codigo_producto")
    private Product product;

    @Column(name = "cantidad",length = 11)
    private int amount;

    @Column(name = "precio", length = 15)
    private double price;

    @Column(name = "numero_linea", length = 6)
    private int lineNumber;
}
