package kz.maks.barter.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-28
 */
public class ProductListResponse extends GoodResponse {
    private List<ProductDto> products = new ArrayList<>();

    public List<ProductDto> getProducts() {
        return products;
    }
}
