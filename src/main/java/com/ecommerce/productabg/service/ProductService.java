package com.ecommerce.productabg.service;

import com.ecommerce.productabg.dto.ProductDto;
import com.ecommerce.productabg.repository.ProductRepo;
import com.ecommerce.productabg.utility.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    // get all products using the dto
    public Flux<ProductDto> getall(){
        return repo.findAll().map(AppUtility::entityToDto);
    }

    // get product by Id

    public Mono<ProductDto> getbyId(String id){
        return repo.findById(id).map(AppUtility::entityToDto);
    }

    //get by range (min, max)

    public Flux<ProductDto> getByRange(double min,double max){
        return repo.findByPriceBetween(Range.closed(min,max));
    }

    // save a product

    public Mono<ProductDto> save(Mono<ProductDto> productDto){
        return productDto.map(AppUtility::dtoToEntity).flatMap(repo::insert).map(AppUtility::entityToDto);
    }

    //  updating a product

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id ){
        return repo.findById(id).flatMap(product -> productDtoMono.map(AppUtility::dtoToEntity).doOnNext(e->e.setId(id)))
                .flatMap(repo::save).map(AppUtility::entityToDto);

    }

    // deleting a product

    public Mono<Void> deleteProduct(String id){
        return repo.deleteById(id);
    }

}
