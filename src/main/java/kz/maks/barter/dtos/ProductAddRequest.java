package kz.maks.barter.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
public class ProductAddRequest {
    @NotNull(message = "product.add.userId.required")
    private Long userId;

    @NotNull(message = "product.add.title.required")
    @Size(min = 1, message = "product.add.title.required")
    private String title;

    @NotNull(message = "product.add.price.required")
    private BigDecimal price;

    @NotNull(message = "product.add.photo.required")
    @Size(min = 1, message = "product.add.photo.required")
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
