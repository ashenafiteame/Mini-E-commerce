package com.ecommerce.productabg.utility;

import com.ecommerce.productabg.dto.ProductDto;
import com.ecommerce.productabg.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtility {
    public static ProductDto entityToDto(Product product){
        ProductDto dto=new ProductDto();
        BeanUtils.copyProperties(product,dto);
        return dto;
    }
    public static Product dtoToEntity(ProductDto dto){
        Product product=new Product();
        BeanUtils.copyProperties(dto,product);
        return product;
    }

}
