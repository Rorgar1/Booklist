package com.launchcode.booklist.controllers;

        import com.launchcode.booklist.models.BookRating;
        import com.launchcode.booklist.models.data.BookRatingDao;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;

        import javax.validation.Valid;

@Controller
@RequestMapping("bookRating")
    public class BookRatingController {


    @Autowired
    BookRatingDao bookRatingDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(defaultValue = "0") int bookRatingId) {
        model.addAttribute("title", "BookRatings");
        model.addAttribute("bookRatings", bookRatingDao.findAll());
        return "bookRating/index";
    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new BookRating());
        model.addAttribute("title", "Add Rating Category");
        return "bookRating/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid BookRating bookRating , Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Rating Category");
            return "bookRating/add";
        }

        bookRatingDao.save(bookRating);
        return "redirect:";
    }

}