package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.web.model.ProductDto;

import java.util.UUID;

public interface ProductService {
    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductDto productDto);

    void updateProduct(UUID productId, ProductDto productDto);

    void deleteById(UUID productId);
}
