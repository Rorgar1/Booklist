package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.User;
import com.launchcode.booklist.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());

        return "user/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify_password) {
        model.addAttribute("user", user);

        if (errors.hasErrors()) {
            return "user/add";
        }

        if (user.getPassword().equals(verify_password)) {
            userDao.save(user);
            return "user/index";
        }

        user.setPassword("");
        model.addAttribute("error_message", "Passwords don't match. Please try again.");
        return "user/add";

    }
}
