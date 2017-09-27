package kz.maks.barter.controllers;

import kz.maks.barter.dao.ProductDao;
import kz.maks.barter.dao.UserDao;
import kz.maks.barter.dtos.BaseResponse;
import kz.maks.barter.dtos.ProductAddResponse;
import kz.maks.barter.entities.Product;
import kz.maks.barter.dtos.ProductAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    public Long list(@NotNull(message = "product.list.userId.required") Long userId) {
        //TODO get list of available products to match for userId
        return userId;
    }

}
