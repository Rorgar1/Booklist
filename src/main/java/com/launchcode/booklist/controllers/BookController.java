package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.Book;
//import com.launchcode.booklist.models.BookData;
import com.launchcode.booklist.models.BookRating;
import com.launchcode.booklist.models.User;
import com.launchcode.booklist.models.data.BookDao;
import com.launchcode.booklist.models.data.BookRatingDao;
import com.launchcode.booklist.models.data.UserDao;
import javassist.NotFoundException;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookRatingDao bookRatingDao;

    @Autowired
    private UserDao userDao;

    //request path: book/
    @RequestMapping(value = "")
    public String index(Model model,
                        @CookieValue(value = "user", defaultValue = "none") String username) {

        if (username.equals("none")) {
            return "redirect:/user/login";
        }

        List<User> users = userDao.findByUsername(username);
        if (users.isEmpty()) {
            return "redirect:/user/login";
        }
        User loggedInUser = users.get(0);
        List<Book> books = bookDao.findByUserId(loggedInUser.getId());

        model.addAttribute("books", books);
        model.addAttribute("title", "My Books");
        return "book/index";
    }

    //request path book/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddBookForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if (username.equals("none")) {
            return "redirect:/user/login";
        }

        model.addAttribute("title", "Add Book");
        model.addAttribute(new Book());
        model.addAttribute("bookRatings", bookRatingDao.findAll());
        return "book/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBookForm(@ModelAttribute @Valid Book newBook,
                                     Errors errors,
                                     Model model, @CookieValue(value = "user", defaultValue = "none") String username) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Book");
            model.addAttribute("bookRatings", bookRatingDao.findAll());
            return "book/add";
        }

        if (username.equals("none")) {
            return "redirect:/user/login";
        }

        List<User> users = userDao.findByUsername(username);
        if (users.isEmpty()) {
            return "redirect:/user/login";
        }
        User loggedInUser = users.get(0);

        newBook.setUser(loggedInUser);
        bookDao.save(newBook);
        return "redirect:";
    }

    //remove book
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveBookForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if (username.equals("none")) {
            return "redirect:/user/login";
        }
        model.addAttribute("title", "Remove Book");
        model.addAttribute("books", bookDao.findAll());
        return "book/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveBookForm(@RequestParam int[] bookIds) {
        for (int bookId : bookIds) {
            bookDao.deleteById(bookId);
        }
        return "redirect:";
    }


    //edit booklist

    @RequestMapping(value = "edit/{bookId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable("bookId") int bookId) throws NotFoundException {
        Optional<Book> optionalBook = bookDao.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new NotFoundException("Book does not exist");
        }
        Book book = optionalBook.get();
        model.addAttribute("book", book);
        model.addAttribute("bookRatings", bookRatingDao.findAll());
        model.addAttribute("title", "Edit Books: " +
                book.getName() + " ( id = " + book.getId() + " ) ");

        return "book/edit";
    }

    @RequestMapping(value = "edit/{bookId}", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute @Valid Book editedBook, @PathVariable("bookId") int bookId) {

        editedBook.setId(bookId);
        bookDao.save(editedBook);
        return "redirect:/book";
    }


}


