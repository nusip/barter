package kz.maks.barter.dao;

import kz.maks.barter.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
