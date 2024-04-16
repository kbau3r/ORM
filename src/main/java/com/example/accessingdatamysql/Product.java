package com.example.accessingdatamysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity // Steht für klasse bzw. das daraus eben ein objekt wird
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) // Generierung von ID automatisch
    private int id;
    private String name;
    private Integer price;

    @ManyToOne  // Ein Produkt kann in einem Warehouse liegen, aber ien Warehouse kann mehrere Produkte haben
    @JsonIgnore
    private Warehouse warehouse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}