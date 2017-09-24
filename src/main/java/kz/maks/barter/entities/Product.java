package kz.maks.barter.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@Entity
public class Product extends IdEntity {
    @ManyToOne
    private User user;
    private String title;
    private BigDecimal price;
    private String picture;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
