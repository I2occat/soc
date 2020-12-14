package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.domain.Customer;
import de.unikassel.soc.platform.domain.Product;
import de.unikassel.soc.platform.repos.ProductRepo;
import de.unikassel.soc.platform.repos.SellerRepo;
import de.unikassel.soc.platform.web.mappers.ProductMapper;
import de.unikassel.soc.platform.web.mappers.ProductMapperImpl;
import de.unikassel.soc.platform.web.mappers.SellerMapper;
import de.unikassel.soc.platform.web.model.CustomerDto;
import de.unikassel.soc.platform.web.model.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo repo;
    private ProductMapper mapper = new ProductMapperImpl();

    @Override
    public ProductDto getProductById(UUID productId) {
        Product product = repo.findById(productId).get();
        return mapper.productToProductDto(product);
    }

    @Override
    public ProductDto saveNewProduct(ProductDto productDto) {
        Product product = mapper.productDtoToProduct(productDto);
        repo.save(product);
        return productDto;
    }

    @Override
    public void updateProduct(UUID productId, ProductDto productDto) {
        Product product = repo.findById(productId).get();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCurrency(productDto.getCurrency());
    }

    @Override
    public void deleteById(UUID productId) {
        repo.deleteById(productId);
    }

    public ProductDto getProductByPrice(Double from, Double to) {
        List<Product> products = repo.findProductByPriceBetween(from, to);
        if(products.size()>0) {
            return mapper.productToProductDto(products.get(0));
        }
        return null;
    }
}
