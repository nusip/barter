package kz.maks.barter.dtos;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public class ProductAddForm {
    @NotNull
    private Long userId;
    @NotNull
    private String title;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String photo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
