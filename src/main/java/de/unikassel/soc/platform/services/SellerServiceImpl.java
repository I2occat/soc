package de.unikassel.soc.platform.services;

import de.unikassel.soc.platform.domain.Customer;
import de.unikassel.soc.platform.domain.Seller;
import de.unikassel.soc.platform.repos.CustomerRepo;
import de.unikassel.soc.platform.repos.SellerRepo;
import de.unikassel.soc.platform.web.mappers.CustomerMapper;
import de.unikassel.soc.platform.web.mappers.SellerMapper;
import de.unikassel.soc.platform.web.mappers.SellerMapperImpl;
import de.unikassel.soc.platform.web.model.CustomerDto;
import de.unikassel.soc.platform.web.model.SellerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepo repo;
    private SellerMapper mapper = new SellerMapperImpl();

    @Override
    public SellerDto getSellerById(UUID sellerId) {
        Seller seller = repo.findById(sellerId).get();
        return mapper.sellerToSellerDto(seller);
    }

    @Override
    public SellerDto saveNewSeller(SellerDto sellerDto) {
        Seller seller = mapper.sellerDtoToSeller(sellerDto);
        repo.save(seller);
        return sellerDto;
    }

    @Override
    public void updateSeller(UUID sellerId, SellerDto sellerDto) {
        Seller seller = repo.findById(sellerId).get();
        seller.setName(sellerDto.getName());
    }

    @Override
    public void deleteById(UUID sellerId) {
        repo.deleteById(sellerId);
    }

    public SellerDto getSellerByName(String sellerName) {
        List<Seller> sellers = repo.findByName(sellerName);
        if(sellers.size()>0) {
            return mapper.sellerToSellerDto(sellers.get(0));
        }
        return null;
    }
}
