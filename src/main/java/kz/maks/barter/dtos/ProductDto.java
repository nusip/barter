package kz.maks.barter.dtos;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-28
 */
public class ProductDto {
    private Long id;
    private String title;
    private String price;
    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
