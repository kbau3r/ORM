package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/insert")
    public ResponseEntity<?> insertRandomWarehouse() {
        try {
            Warehouse warehouse = createRandomWarehouse();

            warehouseRepository.save(warehouse);
            return new ResponseEntity<>("Zufälliges Warehouse wurde hinzugefügt", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<?> retrieveWarehouse(@PathVariable("id") Integer id) {
        try {
            Warehouse warehouse = warehouseRepository.findById(id).orElse(null);
            if (warehouse != null) {
                return new ResponseEntity<>(warehouse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Warehouse nicht gefunden", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Kein warehouse bekommen", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllWarehouses() {
        try {
            List<Warehouse> warehouses = warehouseRepository.findAll();
            if (!warehouses.isEmpty()) {
                return new ResponseEntity<>(warehouses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Kein warehouse gefunden", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("keine warehouses bekommen", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Warehouse createRandomWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Warehousetest");
        warehouse.setAddress("Wexstrasse 123");

        // Zufällige Produkte werden erstellt
        List<Product> products = generateRandomProducts();
        for (Product product : products) {
            product.setWarehouse(warehouse);
        }
        warehouse.setProducts(products);

        return warehouse;
    }

    private List<Product> generateRandomProducts() {
        List<Product> products = new ArrayList<>();
        Random random = new Random();
        int numProducts = random.nextInt(10) + 1; // Zufällige Nummer von 1 bis 10

        for (int i = 0; i < numProducts; i++) {
            Product product = new Product();
            product.setName("Product " + (i + 1));
            product.setPrice(random.nextInt(500)); // Zufälliger preis bis 500
            products.add(product);
        }
        return products;
    }
}