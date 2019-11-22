package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.User;
import com.launchcode.booklist.models.UserData;
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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    // Request path: cheese/
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        User user = new User();
        model.addAttribute(user);
        model.addAttribute("title", "Add User");
        model.addAttribute("error", null);
        model.addAttribute("passMessage", "");
        model.addAttribute("alphaMessage", "");
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid User user, Errors errors, Model model, String verify){

        if (errors.hasErrors()) {
            model.addAttribute("error", errors);
            return "user/add";
        }

        if (!user.getPassword().equals(verify)) {
            model.addAttribute("passMessage", "passwords do not match");
            return "user/add";
        }
        if(!user.getUsername().matches("[a-zA-Z]*")) {
            model.addAttribute("alphaMessage", "username must only have letters");
            return "user/add";
        }
        model.addAttribute("title", "users");
        UserData.add(user);
        model.addAttribute("users", UserData.getAll());
        return "user/index";


    }
}
