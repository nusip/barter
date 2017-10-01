package kz.maks.barter.controllers;

import kz.maks.barter.assemblers.ProductDtoAssembler;
import kz.maks.barter.dao.InterestDao;
import kz.maks.barter.dao.ProductDao;
import kz.maks.barter.dao.UserDao;
import kz.maks.barter.dtos.GoodResponse;
import kz.maks.barter.dtos.ProductAddResponse;
import kz.maks.barter.dtos.ProductDto;
import kz.maks.barter.dtos.ProductInterestResponse;
import kz.maks.barter.dtos.ProductListResponse;
import kz.maks.barter.entities.Interest;
import kz.maks.barter.entities.Product;
import kz.maks.barter.dtos.ProductAddRequest;
import kz.maks.barter.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@RestController
@RequestMapping("/product")
@Validated
@Transactional
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InterestDao interestDao;

    @Autowired
    private ProductDtoAssembler productDtoAssembler;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public GoodResponse add(@Valid ProductAddRequest product) {
        Product p = new Product();
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setUser(userDao.getOne(product.getUserId()));
        p.setPicture(product.getPhoto());
        p = productDao.save(p);
        return new ProductAddResponse(p.getId());
    }

    //TODO test
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ProductListResponse list(@NotNull(message = "product.list.userId.required") Long userId) {
        ProductListResponse response = new ProductListResponse();
        List<Product> productsToMatch = productDao.findProductsToMatch(userId, new PageRequest(0, 5));
        for (Product product : productsToMatch) {
            ProductDto productDto = productDtoAssembler.assemble(product);
            response.getProducts().add(productDto);
        }
        return response;
    }

    //TODO test
    @RequestMapping(value = "/interest", method = RequestMethod.POST)
    public GoodResponse interest(
            @NotNull(message = "product.interest.userId.required") Long userId,
            @NotNull(message = "product.interest.productId.required") Long productId,
            @NotNull(message = "product.interest.positive.required") Boolean positive) {
        Product product = productDao.getOne(productId);
        User user = userDao.getOne(userId);
        Interest interest = new Interest(user, product, positive);
        interestDao.save(interest);

        Optional<Interest> reverseInterest = interestDao.findByUserIdAndProductUserId(product.getUser().getId(), userId);
        if (reverseInterest.isPresent()) {
            Product matchedProduct = reverseInterest.get().getProduct();
            ProductDto productDto = productDtoAssembler.assemble(product);
            ProductDto matchedProductDto = productDtoAssembler.assemble(matchedProduct);
            return new ProductInterestResponse(productDto, matchedProductDto);
        }
        return new GoodResponse();
    }

}
