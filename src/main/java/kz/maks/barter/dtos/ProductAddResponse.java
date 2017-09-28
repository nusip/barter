package kz.maks.barter.dtos;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-27
 */
public class ProductAddResponse extends BaseResponse {
    private Long productId;

    public ProductAddResponse(Long productId) {
        super(hasErrors);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
