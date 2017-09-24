package kz.maks.barter.dao;

import kz.maks.barter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public interface UserDao extends JpaRepository<User, Long> {
}
