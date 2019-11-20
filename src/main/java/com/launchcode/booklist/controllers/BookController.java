package com.launchcode.booklist.controllers;

import com.launchcode.booklist.models.Book;
//import com.launchcode.booklist.models.BookData;
import com.launchcode.booklist.models.BookRating;
import com.launchcode.booklist.models.data.BookDao;
import com.launchcode.booklist.models.data.BookRatingDao;
import org.graalvm.compiler.lir.LIRInstruction;
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
    private BookRatingDao bookRatingDao;

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
        model.addAttribute("bookRatingId", 0);
        model.addAttribute("bookRatings", bookRatingDao.findAll());
        return "book/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBookForm(@ModelAttribute @Valid Book newBook,
                                     Errors errors,
                                    // @RequestParam int bookRatingId,
                                     Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Book");
            model.addAttribute("bookRatings", bookRatingDao.findAll());
            return "book/add";
        }
 /*       Optional<BookRating> optionalBookRating = bookRatingDao.findById(bookRatingId);
        BookRating bookRating = optionalBookRating.get();
        newBook.setBookRating(bookRating); */
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

        Optional<BookRating> optionalBookRating = bookRatingDao.findById(bookRatingId);
        BookRating bookRating = optionalBookRating.get();
        List<Book> books = bookRating.getBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", "Books in Rating Category" +
                bookRating.getName());
        return "book/index";
    }

    //edit booklist

    @RequestMapping(value = "edit/{bookId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int bookRatingId) {
        Optional<Book> optionalBook = bookDao.findById(bookRatingId);
        Book book = optionalBook.get();
        model.addAttribute("book", bookDao.findById(bookRatingId));
        model.addAttribute("bookRatings", bookRatingDao.findById(bookRatingId));
        model.addAttribute("title", "Edit Books: " +
                book.getName() + " ( id = " + book.getId() + " ) ");

        return "book/edit";
    }
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int id, @RequestParam String name,
                                  @RequestParam String authorName,
                                  @RequestParam BookRating bookRating) {
        Optional<Book> optionalBook = bookDao.findById(id);
        Book book = optionalBook.get();
        book.setAuthorName(authorName);
        book.setName(name);
        book.setBookRating(bookRating);

        return "redirect:/book";
    }


    }


