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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user,
                      Errors errors,
                      String verify_password) {
        model.addAttribute("user", user);
        List<User> sameName = userDao.findByUsername(user.getUsername());

        if (errors.hasErrors()) {
            return "user/add";
        }

        if (!sameName.isEmpty()) {
            model.addAttribute("message", "Username is already taken. Please choose another");
        }
        if (user.getPassword().equals(verify_password) && sameName.isEmpty()) {
            userDao.save(user);
            return "user/index";
        }

        user.setPassword("");
        model.addAttribute("error_message", "Passwords don't match or username is already taken. Please try again.");
        return "user/add";
    }

    @RequestMapping(value = "login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, HttpServletResponse response) {
        List<User> users = userDao.findByUsername(user.getUsername());
        if (users.isEmpty()) {
            model.addAttribute("message", "Invalid Username");
            model.addAttribute("title", "Login");
            return "user/login";
        }
        User loggedIn = users.get(0);
        if (loggedIn.getPassword().equals(user.getPassword())) {

            Cookie c = new Cookie("user", user.getUsername());
            c.setPath("/");
            response.addCookie(c);
            return "redirect:/book";
        } else {
            model.addAttribute("message", "Invalid Username");
            model.addAttribute("title", "Login");
            return "user/login";
        }
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
            }
        }
        return "user/login";
    }
}
