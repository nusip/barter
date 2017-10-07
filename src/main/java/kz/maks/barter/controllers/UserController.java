package kz.maks.barter.controllers;

import kz.maks.barter.dao.UserDao;
import kz.maks.barter.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@RestController
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Long register(@RequestParam String phoneNumber) {
        User user = new User();
        user.setPhone(phoneNumber);
        user = userDao.save(user);
        return user.getId();
    }

}
