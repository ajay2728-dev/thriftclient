package com.example.thriftclient.controller;

import com.example.thriftclient.generated.Product;
import com.example.thriftclient.service.ProductClientService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductClientService productClientService;


    @GetMapping("/products")
    List<Product> getProducts(){
        return productClientService.getProduct();
    }

    @GetMapping("/products/{prodId}")
    Product getProductById(@PathVariable int prodId){
        return productClientService.getProductById(prodId);
    }

    @PostMapping("products")
    public void addProduct(@RequestBody Product product){
        log.info("controller adding product..");
        productClientService.addProduct(product);
    }

    @PutMapping("products")
    public void updateProduct(@RequestBody Product product){
        productClientService.updateProduct(product);
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable int prodId) throws TException {
        productClientService.deleteProduct(prodId);
    }

}
