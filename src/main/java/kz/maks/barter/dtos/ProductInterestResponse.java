package kz.maks.barter.dtos;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-29
 */
public class ProductInterestResponse extends GoodResponse {
    private ProductDto userProduct;
    private ProductDto matchedProduct;

    public ProductInterestResponse(ProductDto userProduct, ProductDto matchedProduct) {
        this.userProduct = userProduct;
        this.matchedProduct = matchedProduct;
    }
}
