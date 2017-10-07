package kz.maks.barter.dao;

import kz.maks.barter.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public interface ProductDao extends JpaRepository<Product, Long> {

    //TODO order by: reverse interested first
    @Query("select p from Product p where p.id not in (select i.product.id from Interest i where i.user.id = ?1)")
    List<Product> findProductsToMatch(Long userId, Pageable pageable);

//    @Query("select i.product from Interest i join i.product p where i.positive = true and p.user.id = ?1 and i.user.id in " +
//            "(select p.user.id from Interest i join i.product.p where positive = true and i.user.id = ?1)")
//    List<Product> findMatchedProducts(Long userId);

}
