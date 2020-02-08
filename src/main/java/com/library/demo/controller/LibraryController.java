package com.library.demo.controller;


import com.library.demo.Domain.User;
import com.library.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {


    @Autowired
    private LibraryService libraryService;

    @RequestMapping(method = RequestMethod.GET, value = "/checkBookAvailability/{name}")
    public boolean checkAvailabilityOfBook(@PathVariable String name) {
        return libraryService.checkAvailabilityOfBook(name);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    public String addUser(@RequestBody User user) {
        return libraryService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reserveBook/{userName}/{bookName}")
    public String reserveBook(@PathVariable String userName, @PathVariable String bookName) {
        return libraryService.reserveBook(userName, bookName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/unReserveBook/{userName}/{bookName}")
    public String unreserveBook(@PathVariable String userName, @PathVariable String bookName) {
        return libraryService.unreserveBook(userName, bookName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/issueBook/{userName}/{bookName}")
    public String issueBook(@PathVariable String userName, @PathVariable String bookName) {
        return libraryService.issueBook(userName, bookName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/returnBook/{userName}/{bookName}")
    public String returnBook(@PathVariable String userName, @PathVariable String bookName) {
        return libraryService.returnBook(userName, bookName);
    }


}
