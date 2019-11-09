package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.Book;
//import com.launchcode.booklist.models.BookData;
import com.launchcode.booklist.models.BookRating;
import com.launchcode.booklist.models.data.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    //request path: book/
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("title", "My Books");
        return "book/index";
    }

    //request path book/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddBookForm(Model model) {
        model.addAttribute("title", "Add Book");
        model.addAttribute(new Book());
        model.addAttribute("bookRatings", BookRating.values());
        return "book/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBookForm(@ModelAttribute @Valid Book newBook,
                                     Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Book");
            return "book/add";
        }
        bookDao.save(newBook);
        return "redirect:";
    }

    //remove book
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveBookForm(Model model) {
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
    /*
    @RequestMapping(value = "edit/{bookId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int bookId) {
        Book book = BookData.getById(bookId);
        model.addAttribute("book", BookData.getById(bookId));
        model.addAttribute("bookRatings", BookRating.values());
        model.addAttribute("title", "Edit Books: " +
                book.getName() + " ( id = " + book.getBookId() + " ) ");

        return "book/edit";
    }
    @RequestMapping(value = "edit/{bookId}", method = RequestMethod.POST)
    public String processEditForm(int bookId, String name, String authorName, BookRating rating) {
        Book book = BookData.getById(bookId);
        book.setAuthorName(authorName);
        book.setName(name);
        book.setRating(rating);

        return "redirect:/book";
    } */


    }


