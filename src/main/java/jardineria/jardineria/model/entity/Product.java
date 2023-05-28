package jardineria.jardineria.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "producto",indexes = @Index(name = "idx_name", columnList = "name", unique = true))
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto",length = 15)
    private Long id;

    @NotNull
    @Column(name = "nombre", length = 70)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Column(name = "gama")
    private GammaProduct gamma;

    @Column(name = "dimensiones", length = 25)
    private String dimentions;

    @Column(name = "proveedor", length = 50)
    private String supplier;

    @Column(name = "descripcion")
    private String description;

    @NotNull
    @Column(name = "cantidad_en_stock", length = 6)
    private int amountStock;

    @NotNull
    @Column(name = "precio_venta")
    private double priceSell;

    @Column(name = "precio_proveedor")
    private double priceSuppler;




}
