package com.ecommerce.productabg.controller;

import com.ecommerce.productabg.dto.ProductDto;
import com.ecommerce.productabg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class Controller {
    @Autowired
    private ProductService service;
    @GetMapping
    public Flux<ProductDto> getall(){
        return service.getall();
    }
    @GetMapping("/get/{id}")
    public Mono<ProductDto> get(@PathVariable String id){
        return service.getbyId(id);
    }
    @GetMapping("/range")
    public Flux<ProductDto> getRange(@RequestParam("min") double min,@RequestParam("max") double max){
        return service.getByRange(min,max);
    }
    @PostMapping("/add")
    public Mono<ProductDto> save(@RequestBody Mono<ProductDto> productDtoMono){
        return service.save(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> update(Mono<ProductDto> productDtoMono, String id){
        return service.updateProduct(productDtoMono,id);
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }

}
