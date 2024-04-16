package com.example.accessingdatamysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;

@Entity // Steht für klasse bzw. das daraus eben ein objekt wird
public class Warehouse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) // Generierung von ID automatisch
    private Integer id;

    private String name;

    private String adress;

    @OneToMany(cascade = CascadeType.ALL) // Cascade ist das alle einstellungen
    @JsonIgnore
    private List<Product> products;

    public Warehouse(Integer id, String name, String adress, ArrayList<Product> products) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.products = products;
    }

    public Warehouse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAddress(String adress) {
        this.adress = adress;
    }

    public List<Product> getProdcuts() {
        return products;
    }

    public void setProdcuts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}