package de.unikassel.soc.platform.web.controller;

import de.unikassel.soc.platform.services.SellerService;
import de.unikassel.soc.platform.web.model.SellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/seller")
@RestController
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerDto> getSeller(@PathVariable("sellerId")  UUID sellerId){

        return new ResponseEntity<>(sellerService.getSellerById(sellerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody @Validated SellerDto sellerDto){
        SellerDto savedDto = sellerService.saveNewSeller(sellerDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/seller/" + savedDto.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{sellerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@PathVariable("sellerId") UUID sellerId, @Validated @RequestBody SellerDto sellerDto){
        sellerService.updateSeller(sellerId, sellerDto);
    }

    @DeleteMapping("/{sellerId}")
    public void deleteById(@PathVariable("sellerId")  UUID sellerId){
        sellerService.deleteById(sellerId);
    }

}
