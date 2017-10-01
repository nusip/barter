package kz.maks.barter.dtos;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-27
 */
public class ProductAddResponse extends GoodResponse {
    private Long productId;

    public ProductAddResponse(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
