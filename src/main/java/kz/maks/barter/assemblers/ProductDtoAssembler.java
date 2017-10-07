package kz.maks.barter.assemblers;

import kz.maks.barter.dtos.ProductDto;
import kz.maks.barter.entities.Product;
import org.springframework.stereotype.Component;

/**
 * @author Maksat Nusipzhan
 * @version 2017-10-02
 */
@Component
public class ProductDtoAssembler {

    public ProductDto assemble(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(String.valueOf(product.getPrice()));
        productDto.setPhoto(product.getPicture());
        return productDto;
    }

}
