package by.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Long count;

    @Column(name = "price_for_one")
    private Long priceForOne;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}
