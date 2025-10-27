package com.ecommerce.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
public class ProductEntity {

    @Id
    private UUID id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StockEntity> stocks = new HashSet<>();

    private StockEntity getStockWithMinSoldPrice(){
        return this.stocks.stream()
                .filter(s -> s.getStatus().equals(StockStatus.AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
    }

    public StockEntity decStock(){
        var stock = getStockWithMinSoldPrice();
        stock.decAmount();
        return stock;
    }

    public BigDecimal getPrice(){
        return getStockWithMinSoldPrice().getSoldPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }
}
