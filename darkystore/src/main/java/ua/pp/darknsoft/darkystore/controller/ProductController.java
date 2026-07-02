package ua.pp.darknsoft.darkystore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.darkystore.dto.ProductRecord;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<ProductRecord>> getProducts(@RequestHeader Map<String,String> headers) { // DTO Pattern
        ProductRecord p1 = new ProductRecord(1L, "OLOLO");
        ProductRecord p2 = new ProductRecord(2L, "BOLOLO");
        List<ProductRecord> productList = List.of(p1, p2);
        return ResponseEntity.ok(productList);
    }
}
