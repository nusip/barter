package kz.maks.barter.controllers;

import kz.maks.barter.dao.ProductDao;
import kz.maks.barter.dao.UserDao;
import kz.maks.barter.entities.Product;
import kz.maks.barter.dtos.ProductAddForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Long add(@Valid @ModelAttribute ProductAddForm product, BindingResult result) {
        if (result.hasErrors()) {
            //TODO
        }
        Product p = new Product();
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setUser(userDao.getOne(product.getUserId()));
        p.setPicture(product.getPhoto());
        p = productDao.save(p);
        return p.getId();
    }

}
