package com.launchcode.booklist.controllers;

        import com.launchcode.booklist.models.Book;
        import com.launchcode.booklist.models.BookRating;
        import com.launchcode.booklist.models.data.BookRatingDao;
        import javassist.NotFoundException;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.List;
        import java.util.Optional;

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
    //view books by rating
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String displayRatingsForm(Model model, @PathVariable("id") int id) throws NotFoundException {

        Optional<BookRating> optionalBookRating = bookRatingDao.findById(id);
        if (!optionalBookRating.isPresent()) {
            throw new NotFoundException("Does not exist");
        }
        BookRating bookrating = optionalBookRating.get();
        List<Book> books = bookrating.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", "Books in Category " + bookrating.getName());
        return "book/index";

    }
}