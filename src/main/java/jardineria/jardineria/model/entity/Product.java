package jardineria.jardineria.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "producto",indexes = @Index(name = "idx_nombre", columnList = "nombre", unique = true))
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto",length = 15,nullable = false)
    private Long id;

    @Column(name = "nombre", length = 70,nullable  = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "gama_id", referencedColumnName = "gama",nullable = false)
    private GammaProduct gamma;

    @Column(name = "dimensiones", length = 25,nullable = true)
    private String dimentions;

    @Column(name = "proveedor", length = 50,nullable = true)
    private String supplier;

    @Column(name = "descripcion",nullable = true)
    private String description;

    @Column(name = "cantidad_en_stock", length = 6,nullable = false)
    private int amountStock;

    @Column(name = "precio_venta",nullable = false)
    private double priceSell;

    @Column(name = "precio_proveedor",nullable = true)
    private double priceSuppler;




}
