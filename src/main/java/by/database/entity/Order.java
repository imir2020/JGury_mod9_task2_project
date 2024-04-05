package by.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "count_product")
    private Long countProduct;

    @Column(name = "price_product")
    private Long priceProduct;

    @Column(name = "date_order")
    private LocalDate dateOrder;

}
