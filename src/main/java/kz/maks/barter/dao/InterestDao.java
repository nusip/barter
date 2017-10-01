package kz.maks.barter.dao;

import kz.maks.barter.entities.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public interface InterestDao extends JpaRepository<Interest, Long> {

    Optional<Interest> findByUserIdAndProductUserId(Long userId, Long productUserId);

}
