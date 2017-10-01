package kz.maks.barter.entities;

import javax.persistence.Column;
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
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Product product;
    @Column(nullable = false)
    private Boolean positive;

    public Interest(User user, Product product, Boolean positive) {
        this.user = user;
        this.product = product;
        this.positive = positive;
    }

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
