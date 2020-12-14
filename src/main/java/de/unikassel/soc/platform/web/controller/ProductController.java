package de.unikassel.soc.platform.web.controller;

import de.unikassel.soc.platform.services.ProductService;
import de.unikassel.soc.platform.web.model.ProductDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/{productId}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") UUID productId){

        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @PostMapping // POST - create new product
    public ResponseEntity handlePost(@Valid @RequestBody ProductDto productDto){

        ProductDto savedDto = productService.saveNewProduct(productDto);

        HttpHeaders headers = new HttpHeaders();
        //todo add hostname to url
        headers.add("Location", "/api/v1/product/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{productId}"})
    public ResponseEntity handleUpdate(@PathVariable("productId") UUID productId, @Valid @RequestBody ProductDto productDto){

        productService.updateProduct(productId, productDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") UUID productId){
        productService.deleteById(productId);
    }

}
