package kz.maks.barter.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-28
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "product_id"}))
public class Interest extends IdEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private Boolean positive;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Boolean getPositive() {
        return positive;
    }

    public void setPositive(Boolean positive) {
        this.positive = positive;
    }
}
