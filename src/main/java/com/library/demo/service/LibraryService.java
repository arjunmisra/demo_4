package com.library.demo.service;

import com.library.demo.Domain.Book;
import com.library.demo.Domain.User;
import com.library.demo.configuration.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private AppConfig appConfig;

    public boolean checkAvailabilityOfBook(String name) {
        Book book = appConfig.getBookByName(name);
        if (book == null) return false;
        return appConfig.checkIfAvailable(book);

    }

    public String addUser(User user) {
        User new_user = user;
        Object isAdded = appConfig.addUser(new_user);
        return isAdded == null ? "The User has been created" : "The user has been updated";

    }

    public String reserveBook(String userName, String bookName) {
        User user = appConfig.getUserByName(userName);
        if (user == null) return "User is not available";
        Book book = appConfig.getBookByName(bookName);
        if (book == null) return "Book is not available";
        if (appConfig.checkIfAvailable(book)) {
            appConfig.reserveBooks(user, book);
            return "Reserved the book";
        }
        return "Cannot reserve book";
    }

    public String unreserveBook(String userName, String bookName) {
        User user = appConfig.getUserByName(userName);
        if (user == null) return "User is not available";
        Book book = appConfig.getBookByName(bookName);
        if (book == null) return "Book is not available";
        if (user.getReservedBooks().contains(book)) {
            appConfig.unReserveBooks(user, book);
            return "Successfully unreserved the book";

        }
        return "Cannot unreserve book";
    }

    public String issueBook(String userName, String bookName) {
        User user = appConfig.getUserByName(userName);
        if (user == null) return "User is not available";
        Book book = appConfig.getBookByName(bookName);
        if (book == null) return "Book is not available";
        if (appConfig.checkIfAvailable(book)) {
            appConfig.issueTheBook(user, book);
            return "Successfully issued the book";

        }
        return "Cannot issue book";
    }

    public String returnBook(String userName, String bookName) {
        User user = appConfig.getUserByName(userName);
        if (user == null) return "User is not available";
        Book book = appConfig.getBookByName(bookName);
        if (book == null) return "Book is not available";
        if (user.getIssuedBooks().contains(book)) {
            appConfig.returnTheBook(user, book);
            return "Successfully returned the book";

        }
        return "Cannot returned book";
    }
}
