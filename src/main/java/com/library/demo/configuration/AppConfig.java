package com.library.demo.configuration;


import com.library.demo.Domain.Book;
import com.library.demo.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class AppConfig {

    ConcurrentHashMap<Book, Integer> availableBooks;

    ConcurrentHashMap<String, Book> nameMappedToBook;
    ConcurrentHashMap<String, User> nameMappedToUser;

    ConcurrentHashMap<String, Book> latestBookIssuedToUser;
    ConcurrentHashMap<String, Book> latestBookReservedForUser;


    @Bean
    public void initializeStorage(){
        availableBooks = new ConcurrentHashMap<>();
        nameMappedToBook = new ConcurrentHashMap<>();
        nameMappedToUser = new ConcurrentHashMap<>();
        latestBookIssuedToUser = new ConcurrentHashMap<>();
        latestBookReservedForUser = new ConcurrentHashMap<>();
    }


    @Bean
    public void initialzeBooks() {
        for (int i = 1; i <= 4; i++) {
            Book book = new Book(i, "Book" + i);
            availableBooks.put(book, 5);
            nameMappedToBook.put(book.getName(), book);
        }
    }

    public Book getBookByName(String bookName) {
        return nameMappedToBook.get(bookName);
    }

    public User getUserByName(String userName) {
        return nameMappedToUser.get(userName);
    }

    public boolean checkIfAvailable(Book book) {
        return availableBooks.containsKey(book) && availableBooks.get(book) > 0;
    }

    public Object addUser(User user) {
        nameMappedToUser.put(user.getName(), user);
        Object isAdded = latestBookIssuedToUser.put(user.getName(), new Book(-1, "dummy"));
        return isAdded;
    }

    public boolean incrementBooks(Book book) {
        if (availableBooks.get(book) == 5)
            return false;
        availableBooks.put(book, availableBooks.get(book) + 1);
        return true;
    }


    public boolean decrementBooks(Book book) {
        if (availableBooks.get(book) == 0)
            return false;
        availableBooks.put(book, availableBooks.get(book) - 1);
        return true;
    }

    public void reserveBooks(User reservedUser, Book reservingBook) {
        latestBookReservedForUser.put(reservedUser.getName(), reservingBook);
        reservedUser.getReservedBooks().add(reservingBook);
        decrementBooks(reservingBook);
    }

    public void unReserveBooks(User reservedUser, Book reservingBook) {
        latestBookReservedForUser.remove(reservedUser.getName());
        reservedUser.getReservedBooks().remove(reservingBook);
        incrementBooks(reservingBook);
    }

    public void issueTheBook(User user, Book book) {
        latestBookIssuedToUser.put(user.getName(), book);
        user.getIssuedBooks().add(book);
        decrementBooks(book);
    }

    public void returnTheBook(User user, Book book) {
        latestBookIssuedToUser.remove(user.getName());
        user.getIssuedBooks().remove(book);
        incrementBooks(book);
    }


}


















