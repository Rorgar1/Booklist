package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.Book;
//import com.launchcode.booklist.models.BookData;
import com.launchcode.booklist.models.data.BookDao;
import com.launchcode.booklist.models.data.BookRatingDao;
import com.launchcode.booklist.models.BookRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @Autowired
    BookRatingDao bookRatingDao;


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
        model.addAttribute("bookRatings", bookRatingDao.findAll());
        return "book/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBookForm(@ModelAttribute @Valid Book newBook,
                                     Errors errors,
                                     @RequestParam int bookRatingId,
                                     Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Book");
            model.addAttribute("bookRatings", bookRatingDao.findAll());
            return "book/add";
        }
        //BookRating bookRating = bookRatingDao.findOne(bookRatingId);
        Optional<BookRating> bookRating = bookRatingDao.findById(bookRatingId);
        newBook.setBookRating(bookRating);
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

    @RequestMapping(value = "bookRating", method = RequestMethod.GET)
    public String bookRating(Model model, @RequestParam int bookRatingId) {

        BookRating bookRating = bookRatingDao.findById(bookRatingId).get();
        List<Book> books = bookRating.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", "Book Ratings " +
                bookRating.getName());
        return "book/index";
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


