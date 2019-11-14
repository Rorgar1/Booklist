package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.data.BookRatingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("bookRating")
public class BookRatingController {

    @Autowired
    BookRatingDao bookRatingDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(defaultValue = "0") int id) {
        model.addAttribute("title" , "BookRatings");
        model.addAttribute("bookRatings", bookRatingDao.findAll());
        return "category/index";
    }

}
