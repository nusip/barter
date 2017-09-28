package kz.maks.barter.controllers;

import kz.maks.barter.dao.InterestDao;
import kz.maks.barter.dao.ProductDao;
import kz.maks.barter.dao.UserDao;
import kz.maks.barter.dtos.BaseResponse;
import kz.maks.barter.dtos.GoodResponse;
import kz.maks.barter.dtos.ProductAddResponse;
import kz.maks.barter.dtos.ProductDto;
import kz.maks.barter.dtos.ProductInterestResponse;
import kz.maks.barter.dtos.ProductListResponse;
import kz.maks.barter.entities.Interest;
import kz.maks.barter.entities.Product;
import kz.maks.barter.dtos.ProductAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InterestDao interestDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse add(@Valid ProductAddRequest product) {
        Product p = new Product();
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setUser(userDao.getOne(product.getUserId()));
        p.setPicture(product.getPhoto());
        p = productDao.save(p);
        return new ProductAddResponse(p.getId());
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ProductListResponse list(@NotNull(message = "product.list.userId.required") Long userId) {
        ProductListResponse response = new ProductListResponse();
        List<Product> productsToMatch = productDao.findProductsToMatch(userId, new PageRequest(0, 5));
        for (Product product : productsToMatch) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(String.valueOf(product.getPrice()));
            productDto.setPhoto(product.getPicture());
            response.getProducts().add(productDto);
        }
        //TODO test
        return response;
    }

    @RequestMapping(value = "/interest", method = RequestMethod.POST)
    public GoodResponse interest(
            @NotNull(message = "product.interest.userId.required") Long userId,
            @NotNull(message = "product.interest.productId.required") Long productId,
            @NotNull(message = "product.interest.positive.required") Boolean positive) {
        Interest interest = new Interest();
        interest.setUser(userDao.getOne(userId));
        interest.setProduct(productDao.getOne(productId));
        interest.setPositive(positive);
        interestDao.save(interest);
        //TODO check match
        return null;
    }

}
