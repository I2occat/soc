package de.unikassel.soc.platform.repos;

import de.unikassel.soc.platform.domain.Customer;
import de.unikassel.soc.platform.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public interface SellerRepo extends JpaRepository<Seller, UUID> {
    List<Seller> findByName(String name);
}